package org.rong.spider.parser.storage;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.rong.spider.util.SpiderConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * Queue for caching output data
 * 
 */
public class WriterQueue {
	final static Logger logger = LoggerFactory.getLogger(WriterQueue.class);
	private LinkedList<String> queue = new LinkedList<String>();
	private Lock lock = new ReentrantLock();
	private Condition notFull = lock.newCondition();
	private Condition notEmpty = lock.newCondition();

	private static WriterQueue manager = new WriterQueue();

	private WriterQueue() {
	}

	public static WriterQueue getQueue() {
		return manager;
	}

	public void put(String outputData) {

		lock.lock();

		try {
			while (queue.size() == SpiderConstants.MAX_QUEUE_SIZE
					&& OutputTask.loop) {
				logger.warn("warning: data queue is full => " + queue.size());
				notFull.await();
			}

			queue.addFirst(outputData);

			notEmpty.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}

	public LinkedList<String> takeAll() {

		LinkedList<String> retVal = new LinkedList<String>();

		lock.lock();

		try {
			while (queue.size() == 0 && OutputTask.loop) {
				notEmpty.await();
			}

			retVal.addAll(queue);
			queue.clear();

			notFull.signal();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
		return retVal;
	}
}
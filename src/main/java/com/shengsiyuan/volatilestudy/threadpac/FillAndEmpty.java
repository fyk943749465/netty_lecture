package com.shengsiyuan.volatilestudy.threadpac;

import thrift.generated.DataException;

import java.awt.image.DataBuffer;
import java.util.concurrent.Exchanger;

public class FillAndEmpty {

//    Exchanger<DataBuffer> exchanger = new Exchanger<DataBuffer>();
//    DataBuffer initialEmptyBuffer = null;
//    DataBuffer initialFullBuffer = null;
//
//    class FillingLoop implements Runnable {
//
//        @Override
//        public void run() {
//            DataBuffer currentBuffer = initialEmptyBuffer;
//            try {
//                while (currentBuffer != null) {
//                    addToBuffer(currentBuffer);
//                    if (currentBuffer.isFull())
//                        currentBuffer = exchanger.exchange(currentBuffer);
//                }
//            } catch (InterruptedException ex) {
//
//            }
//        }
//    }
//
//    class EmptyingLoop implements Runnable {
//
//        @Override
//        public void run() {
//            DataBuffer currentBuffer = initialFullBuffer;
//            try {
//                while (currentBuffer != null) {
//                    takeFromBuffer(currentBuffer);
//                    if (currentBuffer.isEmpty()) {
//                        currentBuffer = exchanger.exchange(currentBuffer);
//                    }
//                }
//            } catch (InterruptedException ex) {
//
//            }
//        }
//    }
//
//    void start() {
//        new Thread(new FillingLoop()).start();
//        new Thread(new EmptyingLoop()).start();
//    }
}

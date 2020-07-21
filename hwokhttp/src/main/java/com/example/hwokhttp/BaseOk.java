package com.example.hwokhttp;

import io.reactivex.Observable;

public interface BaseOk<T> {
     <T> Observable<T> doPostJsonRx();
     <T> Observable<T> doPostFromRx();
     <T> Observable<T> doPost();

     <T> Observable<T> doPostFile();

     <T> Observable<T> doGet();
}

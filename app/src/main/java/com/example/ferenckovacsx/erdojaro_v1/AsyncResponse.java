package com.example.ferenckovacsx.erdojaro_v1;

import com.example.ferenckovacsx.erdojaro_v1.JavaBeans.AsyncTaskResponseContent;

import java.util.List;

/**
 * Created by ferenckovacsx on 2017-06-09.
 */

public interface AsyncResponse {
    void processFinish(List<AsyncTaskResponseContent> output);
}

package com.erdojaro.ferenckovacsx.erdojaro_v1;

import com.erdojaro.ferenckovacsx.erdojaro_v1.javabeans.AsyncTaskResponseContent;

import java.util.List;

/**
 * Created by ferenckovacsx on 2017-06-09.
 */

public interface AsyncResponse {
    void processFinish(List<AsyncTaskResponseContent> output);
}

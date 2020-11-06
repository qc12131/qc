package com.qccloud.core.config;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.handlers.FastjsonTypeHandler;

public class QCFastjsonTypeHandler extends FastjsonTypeHandler {
    public QCFastjsonTypeHandler(Class<Object> type) {
        super(type);
    }

    @Override
    protected String toJson(Object obj) {
        return JSONObject.toJSONString(obj);
    }
}

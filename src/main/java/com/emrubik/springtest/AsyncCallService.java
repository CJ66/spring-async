/*******************************************************************************
 * @(#)LongTimeAsyncCallService.java 2018年8月16日
 *
 * Copyright 2018 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springtest;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2018年8月16日 上午10:11:00
 */
public class AsyncCallService {

    private LongTermTaskCallback callback;
    public AsyncCallService(LongTermTaskCallback callback){
        this.callback = callback;
    }
    
    /**
     * 方法调用回调执行
     * makeRemoteCallAndUnknownWhenFinish 
     * TODO 这里描述这个方法的注意事项 – 可选 TODO 请在每个@param,@return,@throws行尾补充对应的简要说明
     */
    public void makeRemoteCallAndUnknownWhenFinish() {
        System.out.println("完成此任务需要 : " + 3 + " 秒");
        callback.callback("长时间异步调用完成.");
    }
}

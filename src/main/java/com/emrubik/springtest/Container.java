/*******************************************************************************
 * @(#)Container.java 2018年8月16日
 *
 * Copyright 2018 emrubik Group Ltd. All rights reserved.
 * EMRubik PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *******************************************************************************/
package com.emrubik.springtest;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;

/**
 * TODO 这里请补充该类型的简述说明
 * @author <a href="mailto:changj@emrubik.com">chang jiang</a>
 * @version $Revision 1.0 $ 2018年8月16日 上午11:13:28
 */
public class Container implements Serializable{
    
  
    /**
     * serialVersionUID : TODO 这里请补充该字段的简述说明
     */
    private static final long serialVersionUID = 6023494805620192445L;


    /**
     * Create a new instance Container
     */
    private Container() {

    }


    /** Command map */
    public final static ConcurrentHashMap<String, AsyncCallService> callServiceMap = new ConcurrentHashMap<String, AsyncCallService>();
}

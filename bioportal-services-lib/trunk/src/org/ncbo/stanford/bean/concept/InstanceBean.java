package org.ncbo.stanford.bean.concept;

import java.util.List;

public class InstanceBean extends AbstractConceptBean {
    private List<ClassBean> instanceType;

    public void setInstanceType(List<ClassBean> instanceType) {
        this.instanceType = instanceType;
    }

    public List<ClassBean> getInstanceType() {
        return instanceType;
    }

}

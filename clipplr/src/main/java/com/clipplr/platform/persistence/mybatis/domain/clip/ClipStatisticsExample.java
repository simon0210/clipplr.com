package com.clipplr.platform.persistence.mybatis.domain.clip;

import java.util.ArrayList;
import java.util.List;

public class ClipStatisticsExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ClipStatisticsExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Long value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Long value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Long value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Long value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Long value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Long value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Long> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Long> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Long value1, Long value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Long value1, Long value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andClipIdIsNull() {
            addCriterion("clip_id is null");
            return (Criteria) this;
        }

        public Criteria andClipIdIsNotNull() {
            addCriterion("clip_id is not null");
            return (Criteria) this;
        }

        public Criteria andClipIdEqualTo(Long value) {
            addCriterion("clip_id =", value, "clipId");
            return (Criteria) this;
        }

        public Criteria andClipIdNotEqualTo(Long value) {
            addCriterion("clip_id <>", value, "clipId");
            return (Criteria) this;
        }

        public Criteria andClipIdGreaterThan(Long value) {
            addCriterion("clip_id >", value, "clipId");
            return (Criteria) this;
        }

        public Criteria andClipIdGreaterThanOrEqualTo(Long value) {
            addCriterion("clip_id >=", value, "clipId");
            return (Criteria) this;
        }

        public Criteria andClipIdLessThan(Long value) {
            addCriterion("clip_id <", value, "clipId");
            return (Criteria) this;
        }

        public Criteria andClipIdLessThanOrEqualTo(Long value) {
            addCriterion("clip_id <=", value, "clipId");
            return (Criteria) this;
        }

        public Criteria andClipIdIn(List<Long> values) {
            addCriterion("clip_id in", values, "clipId");
            return (Criteria) this;
        }

        public Criteria andClipIdNotIn(List<Long> values) {
            addCriterion("clip_id not in", values, "clipId");
            return (Criteria) this;
        }

        public Criteria andClipIdBetween(Long value1, Long value2) {
            addCriterion("clip_id between", value1, value2, "clipId");
            return (Criteria) this;
        }

        public Criteria andClipIdNotBetween(Long value1, Long value2) {
            addCriterion("clip_id not between", value1, value2, "clipId");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNull() {
            addCriterion("view_count is null");
            return (Criteria) this;
        }

        public Criteria andViewCountIsNotNull() {
            addCriterion("view_count is not null");
            return (Criteria) this;
        }

        public Criteria andViewCountEqualTo(Long value) {
            addCriterion("view_count =", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotEqualTo(Long value) {
            addCriterion("view_count <>", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThan(Long value) {
            addCriterion("view_count >", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountGreaterThanOrEqualTo(Long value) {
            addCriterion("view_count >=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThan(Long value) {
            addCriterion("view_count <", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountLessThanOrEqualTo(Long value) {
            addCriterion("view_count <=", value, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountIn(List<Long> values) {
            addCriterion("view_count in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotIn(List<Long> values) {
            addCriterion("view_count not in", values, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountBetween(Long value1, Long value2) {
            addCriterion("view_count between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andViewCountNotBetween(Long value1, Long value2) {
            addCriterion("view_count not between", value1, value2, "viewCount");
            return (Criteria) this;
        }

        public Criteria andClipCountIsNull() {
            addCriterion("clip_count is null");
            return (Criteria) this;
        }

        public Criteria andClipCountIsNotNull() {
            addCriterion("clip_count is not null");
            return (Criteria) this;
        }

        public Criteria andClipCountEqualTo(Long value) {
            addCriterion("clip_count =", value, "clipCount");
            return (Criteria) this;
        }

        public Criteria andClipCountNotEqualTo(Long value) {
            addCriterion("clip_count <>", value, "clipCount");
            return (Criteria) this;
        }

        public Criteria andClipCountGreaterThan(Long value) {
            addCriterion("clip_count >", value, "clipCount");
            return (Criteria) this;
        }

        public Criteria andClipCountGreaterThanOrEqualTo(Long value) {
            addCriterion("clip_count >=", value, "clipCount");
            return (Criteria) this;
        }

        public Criteria andClipCountLessThan(Long value) {
            addCriterion("clip_count <", value, "clipCount");
            return (Criteria) this;
        }

        public Criteria andClipCountLessThanOrEqualTo(Long value) {
            addCriterion("clip_count <=", value, "clipCount");
            return (Criteria) this;
        }

        public Criteria andClipCountIn(List<Long> values) {
            addCriterion("clip_count in", values, "clipCount");
            return (Criteria) this;
        }

        public Criteria andClipCountNotIn(List<Long> values) {
            addCriterion("clip_count not in", values, "clipCount");
            return (Criteria) this;
        }

        public Criteria andClipCountBetween(Long value1, Long value2) {
            addCriterion("clip_count between", value1, value2, "clipCount");
            return (Criteria) this;
        }

        public Criteria andClipCountNotBetween(Long value1, Long value2) {
            addCriterion("clip_count not between", value1, value2, "clipCount");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}
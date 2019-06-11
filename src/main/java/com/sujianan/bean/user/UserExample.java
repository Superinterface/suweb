package com.sujianan.bean.user;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public UserExample() {
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
            addCriterion("ID is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("ID is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(String value) {
            addCriterion("ID =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(String value) {
            addCriterion("ID <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(String value) {
            addCriterion("ID >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(String value) {
            addCriterion("ID >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(String value) {
            addCriterion("ID <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(String value) {
            addCriterion("ID <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLike(String value) {
            addCriterion("ID like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotLike(String value) {
            addCriterion("ID not like", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<String> values) {
            addCriterion("ID in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<String> values) {
            addCriterion("ID not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(String value1, String value2) {
            addCriterion("ID between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(String value1, String value2) {
            addCriterion("ID not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNull() {
            addCriterion("LOGIN_NAME is null");
            return (Criteria) this;
        }

        public Criteria andLoginNameIsNotNull() {
            addCriterion("LOGIN_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andLoginNameEqualTo(String value) {
            addCriterion("LOGIN_NAME =", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotEqualTo(String value) {
            addCriterion("LOGIN_NAME <>", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThan(String value) {
            addCriterion("LOGIN_NAME >", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_NAME >=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThan(String value) {
            addCriterion("LOGIN_NAME <", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_NAME <=", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameLike(String value) {
            addCriterion("LOGIN_NAME like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotLike(String value) {
            addCriterion("LOGIN_NAME not like", value, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameIn(List<String> values) {
            addCriterion("LOGIN_NAME in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotIn(List<String> values) {
            addCriterion("LOGIN_NAME not in", values, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameBetween(String value1, String value2) {
            addCriterion("LOGIN_NAME between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginNameNotBetween(String value1, String value2) {
            addCriterion("LOGIN_NAME not between", value1, value2, "loginName");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordIsNull() {
            addCriterion("LOGIN_PASSWORD is null");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordIsNotNull() {
            addCriterion("LOGIN_PASSWORD is not null");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordEqualTo(String value) {
            addCriterion("LOGIN_PASSWORD =", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotEqualTo(String value) {
            addCriterion("LOGIN_PASSWORD <>", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordGreaterThan(String value) {
            addCriterion("LOGIN_PASSWORD >", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordGreaterThanOrEqualTo(String value) {
            addCriterion("LOGIN_PASSWORD >=", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordLessThan(String value) {
            addCriterion("LOGIN_PASSWORD <", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordLessThanOrEqualTo(String value) {
            addCriterion("LOGIN_PASSWORD <=", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordLike(String value) {
            addCriterion("LOGIN_PASSWORD like", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotLike(String value) {
            addCriterion("LOGIN_PASSWORD not like", value, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordIn(List<String> values) {
            addCriterion("LOGIN_PASSWORD in", values, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotIn(List<String> values) {
            addCriterion("LOGIN_PASSWORD not in", values, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordBetween(String value1, String value2) {
            addCriterion("LOGIN_PASSWORD between", value1, value2, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andLoginPasswordNotBetween(String value1, String value2) {
            addCriterion("LOGIN_PASSWORD not between", value1, value2, "loginPassword");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNull() {
            addCriterion("USERNAME is null");
            return (Criteria) this;
        }

        public Criteria andUsernameIsNotNull() {
            addCriterion("USERNAME is not null");
            return (Criteria) this;
        }

        public Criteria andUsernameEqualTo(String value) {
            addCriterion("USERNAME =", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotEqualTo(String value) {
            addCriterion("USERNAME <>", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThan(String value) {
            addCriterion("USERNAME >", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameGreaterThanOrEqualTo(String value) {
            addCriterion("USERNAME >=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThan(String value) {
            addCriterion("USERNAME <", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLessThanOrEqualTo(String value) {
            addCriterion("USERNAME <=", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameLike(String value) {
            addCriterion("USERNAME like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotLike(String value) {
            addCriterion("USERNAME not like", value, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameIn(List<String> values) {
            addCriterion("USERNAME in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotIn(List<String> values) {
            addCriterion("USERNAME not in", values, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameBetween(String value1, String value2) {
            addCriterion("USERNAME between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andUsernameNotBetween(String value1, String value2) {
            addCriterion("USERNAME not between", value1, value2, "username");
            return (Criteria) this;
        }

        public Criteria andNetNameIsNull() {
            addCriterion("NET_NAME is null");
            return (Criteria) this;
        }

        public Criteria andNetNameIsNotNull() {
            addCriterion("NET_NAME is not null");
            return (Criteria) this;
        }

        public Criteria andNetNameEqualTo(String value) {
            addCriterion("NET_NAME =", value, "netName");
            return (Criteria) this;
        }

        public Criteria andNetNameNotEqualTo(String value) {
            addCriterion("NET_NAME <>", value, "netName");
            return (Criteria) this;
        }

        public Criteria andNetNameGreaterThan(String value) {
            addCriterion("NET_NAME >", value, "netName");
            return (Criteria) this;
        }

        public Criteria andNetNameGreaterThanOrEqualTo(String value) {
            addCriterion("NET_NAME >=", value, "netName");
            return (Criteria) this;
        }

        public Criteria andNetNameLessThan(String value) {
            addCriterion("NET_NAME <", value, "netName");
            return (Criteria) this;
        }

        public Criteria andNetNameLessThanOrEqualTo(String value) {
            addCriterion("NET_NAME <=", value, "netName");
            return (Criteria) this;
        }

        public Criteria andNetNameLike(String value) {
            addCriterion("NET_NAME like", value, "netName");
            return (Criteria) this;
        }

        public Criteria andNetNameNotLike(String value) {
            addCriterion("NET_NAME not like", value, "netName");
            return (Criteria) this;
        }

        public Criteria andNetNameIn(List<String> values) {
            addCriterion("NET_NAME in", values, "netName");
            return (Criteria) this;
        }

        public Criteria andNetNameNotIn(List<String> values) {
            addCriterion("NET_NAME not in", values, "netName");
            return (Criteria) this;
        }

        public Criteria andNetNameBetween(String value1, String value2) {
            addCriterion("NET_NAME between", value1, value2, "netName");
            return (Criteria) this;
        }

        public Criteria andNetNameNotBetween(String value1, String value2) {
            addCriterion("NET_NAME not between", value1, value2, "netName");
            return (Criteria) this;
        }

        public Criteria andGenderIsNull() {
            addCriterion("GENDER is null");
            return (Criteria) this;
        }

        public Criteria andGenderIsNotNull() {
            addCriterion("GENDER is not null");
            return (Criteria) this;
        }

        public Criteria andGenderEqualTo(Short value) {
            addCriterion("GENDER =", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotEqualTo(Short value) {
            addCriterion("GENDER <>", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThan(Short value) {
            addCriterion("GENDER >", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderGreaterThanOrEqualTo(Short value) {
            addCriterion("GENDER >=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThan(Short value) {
            addCriterion("GENDER <", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderLessThanOrEqualTo(Short value) {
            addCriterion("GENDER <=", value, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderIn(List<Short> values) {
            addCriterion("GENDER in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotIn(List<Short> values) {
            addCriterion("GENDER not in", values, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderBetween(Short value1, Short value2) {
            addCriterion("GENDER between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andGenderNotBetween(Short value1, Short value2) {
            addCriterion("GENDER not between", value1, value2, "gender");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNull() {
            addCriterion("PHONE is null");
            return (Criteria) this;
        }

        public Criteria andPhoneIsNotNull() {
            addCriterion("PHONE is not null");
            return (Criteria) this;
        }

        public Criteria andPhoneEqualTo(String value) {
            addCriterion("PHONE =", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotEqualTo(String value) {
            addCriterion("PHONE <>", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThan(String value) {
            addCriterion("PHONE >", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneGreaterThanOrEqualTo(String value) {
            addCriterion("PHONE >=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThan(String value) {
            addCriterion("PHONE <", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLessThanOrEqualTo(String value) {
            addCriterion("PHONE <=", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneLike(String value) {
            addCriterion("PHONE like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotLike(String value) {
            addCriterion("PHONE not like", value, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneIn(List<String> values) {
            addCriterion("PHONE in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotIn(List<String> values) {
            addCriterion("PHONE not in", values, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneBetween(String value1, String value2) {
            addCriterion("PHONE between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andPhoneNotBetween(String value1, String value2) {
            addCriterion("PHONE not between", value1, value2, "phone");
            return (Criteria) this;
        }

        public Criteria andEmailIsNull() {
            addCriterion("EMAIL is null");
            return (Criteria) this;
        }

        public Criteria andEmailIsNotNull() {
            addCriterion("EMAIL is not null");
            return (Criteria) this;
        }

        public Criteria andEmailEqualTo(String value) {
            addCriterion("EMAIL =", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotEqualTo(String value) {
            addCriterion("EMAIL <>", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThan(String value) {
            addCriterion("EMAIL >", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailGreaterThanOrEqualTo(String value) {
            addCriterion("EMAIL >=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThan(String value) {
            addCriterion("EMAIL <", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLessThanOrEqualTo(String value) {
            addCriterion("EMAIL <=", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailLike(String value) {
            addCriterion("EMAIL like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotLike(String value) {
            addCriterion("EMAIL not like", value, "email");
            return (Criteria) this;
        }

        public Criteria andEmailIn(List<String> values) {
            addCriterion("EMAIL in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotIn(List<String> values) {
            addCriterion("EMAIL not in", values, "email");
            return (Criteria) this;
        }

        public Criteria andEmailBetween(String value1, String value2) {
            addCriterion("EMAIL between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andEmailNotBetween(String value1, String value2) {
            addCriterion("EMAIL not between", value1, value2, "email");
            return (Criteria) this;
        }

        public Criteria andAddressIsNull() {
            addCriterion("ADDRESS is null");
            return (Criteria) this;
        }

        public Criteria andAddressIsNotNull() {
            addCriterion("ADDRESS is not null");
            return (Criteria) this;
        }

        public Criteria andAddressEqualTo(String value) {
            addCriterion("ADDRESS =", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotEqualTo(String value) {
            addCriterion("ADDRESS <>", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThan(String value) {
            addCriterion("ADDRESS >", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressGreaterThanOrEqualTo(String value) {
            addCriterion("ADDRESS >=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThan(String value) {
            addCriterion("ADDRESS <", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLessThanOrEqualTo(String value) {
            addCriterion("ADDRESS <=", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressLike(String value) {
            addCriterion("ADDRESS like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotLike(String value) {
            addCriterion("ADDRESS not like", value, "address");
            return (Criteria) this;
        }

        public Criteria andAddressIn(List<String> values) {
            addCriterion("ADDRESS in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotIn(List<String> values) {
            addCriterion("ADDRESS not in", values, "address");
            return (Criteria) this;
        }

        public Criteria andAddressBetween(String value1, String value2) {
            addCriterion("ADDRESS between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andAddressNotBetween(String value1, String value2) {
            addCriterion("ADDRESS not between", value1, value2, "address");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNull() {
            addCriterion("ID_CARD is null");
            return (Criteria) this;
        }

        public Criteria andIdCardIsNotNull() {
            addCriterion("ID_CARD is not null");
            return (Criteria) this;
        }

        public Criteria andIdCardEqualTo(String value) {
            addCriterion("ID_CARD =", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotEqualTo(String value) {
            addCriterion("ID_CARD <>", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThan(String value) {
            addCriterion("ID_CARD >", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardGreaterThanOrEqualTo(String value) {
            addCriterion("ID_CARD >=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThan(String value) {
            addCriterion("ID_CARD <", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLessThanOrEqualTo(String value) {
            addCriterion("ID_CARD <=", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardLike(String value) {
            addCriterion("ID_CARD like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotLike(String value) {
            addCriterion("ID_CARD not like", value, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardIn(List<String> values) {
            addCriterion("ID_CARD in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotIn(List<String> values) {
            addCriterion("ID_CARD not in", values, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardBetween(String value1, String value2) {
            addCriterion("ID_CARD between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andIdCardNotBetween(String value1, String value2) {
            addCriterion("ID_CARD not between", value1, value2, "idCard");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNull() {
            addCriterion("CREATETIME is null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIsNotNull() {
            addCriterion("CREATETIME is not null");
            return (Criteria) this;
        }

        public Criteria andCreatetimeEqualTo(Date value) {
            addCriterion("CREATETIME =", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotEqualTo(Date value) {
            addCriterion("CREATETIME <>", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThan(Date value) {
            addCriterion("CREATETIME >", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeGreaterThanOrEqualTo(Date value) {
            addCriterion("CREATETIME >=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThan(Date value) {
            addCriterion("CREATETIME <", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeLessThanOrEqualTo(Date value) {
            addCriterion("CREATETIME <=", value, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeIn(List<Date> values) {
            addCriterion("CREATETIME in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotIn(List<Date> values) {
            addCriterion("CREATETIME not in", values, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeBetween(Date value1, Date value2) {
            addCriterion("CREATETIME between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andCreatetimeNotBetween(Date value1, Date value2) {
            addCriterion("CREATETIME not between", value1, value2, "createtime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeIsNull() {
            addCriterion("LOGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeIsNotNull() {
            addCriterion("LOGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andLoginTimeEqualTo(Date value) {
            addCriterion("LOGIN_TIME =", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNotEqualTo(Date value) {
            addCriterion("LOGIN_TIME <>", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeGreaterThan(Date value) {
            addCriterion("LOGIN_TIME >", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("LOGIN_TIME >=", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLessThan(Date value) {
            addCriterion("LOGIN_TIME <", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeLessThanOrEqualTo(Date value) {
            addCriterion("LOGIN_TIME <=", value, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeIn(List<Date> values) {
            addCriterion("LOGIN_TIME in", values, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNotIn(List<Date> values) {
            addCriterion("LOGIN_TIME not in", values, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeBetween(Date value1, Date value2) {
            addCriterion("LOGIN_TIME between", value1, value2, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginTimeNotBetween(Date value1, Date value2) {
            addCriterion("LOGIN_TIME not between", value1, value2, "loginTime");
            return (Criteria) this;
        }

        public Criteria andLoginNumberIsNull() {
            addCriterion("LOGIN_NUMBER is null");
            return (Criteria) this;
        }

        public Criteria andLoginNumberIsNotNull() {
            addCriterion("LOGIN_NUMBER is not null");
            return (Criteria) this;
        }

        public Criteria andLoginNumberEqualTo(Integer value) {
            addCriterion("LOGIN_NUMBER =", value, "loginNumber");
            return (Criteria) this;
        }

        public Criteria andLoginNumberNotEqualTo(Integer value) {
            addCriterion("LOGIN_NUMBER <>", value, "loginNumber");
            return (Criteria) this;
        }

        public Criteria andLoginNumberGreaterThan(Integer value) {
            addCriterion("LOGIN_NUMBER >", value, "loginNumber");
            return (Criteria) this;
        }

        public Criteria andLoginNumberGreaterThanOrEqualTo(Integer value) {
            addCriterion("LOGIN_NUMBER >=", value, "loginNumber");
            return (Criteria) this;
        }

        public Criteria andLoginNumberLessThan(Integer value) {
            addCriterion("LOGIN_NUMBER <", value, "loginNumber");
            return (Criteria) this;
        }

        public Criteria andLoginNumberLessThanOrEqualTo(Integer value) {
            addCriterion("LOGIN_NUMBER <=", value, "loginNumber");
            return (Criteria) this;
        }

        public Criteria andLoginNumberIn(List<Integer> values) {
            addCriterion("LOGIN_NUMBER in", values, "loginNumber");
            return (Criteria) this;
        }

        public Criteria andLoginNumberNotIn(List<Integer> values) {
            addCriterion("LOGIN_NUMBER not in", values, "loginNumber");
            return (Criteria) this;
        }

        public Criteria andLoginNumberBetween(Integer value1, Integer value2) {
            addCriterion("LOGIN_NUMBER between", value1, value2, "loginNumber");
            return (Criteria) this;
        }

        public Criteria andLoginNumberNotBetween(Integer value1, Integer value2) {
            addCriterion("LOGIN_NUMBER not between", value1, value2, "loginNumber");
            return (Criteria) this;
        }

        public Criteria andBeforeLoginTimeIsNull() {
            addCriterion("BEFORE_LOGIN_TIME is null");
            return (Criteria) this;
        }

        public Criteria andBeforeLoginTimeIsNotNull() {
            addCriterion("BEFORE_LOGIN_TIME is not null");
            return (Criteria) this;
        }

        public Criteria andBeforeLoginTimeEqualTo(Date value) {
            addCriterion("BEFORE_LOGIN_TIME =", value, "beforeLoginTime");
            return (Criteria) this;
        }

        public Criteria andBeforeLoginTimeNotEqualTo(Date value) {
            addCriterion("BEFORE_LOGIN_TIME <>", value, "beforeLoginTime");
            return (Criteria) this;
        }

        public Criteria andBeforeLoginTimeGreaterThan(Date value) {
            addCriterion("BEFORE_LOGIN_TIME >", value, "beforeLoginTime");
            return (Criteria) this;
        }

        public Criteria andBeforeLoginTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("BEFORE_LOGIN_TIME >=", value, "beforeLoginTime");
            return (Criteria) this;
        }

        public Criteria andBeforeLoginTimeLessThan(Date value) {
            addCriterion("BEFORE_LOGIN_TIME <", value, "beforeLoginTime");
            return (Criteria) this;
        }

        public Criteria andBeforeLoginTimeLessThanOrEqualTo(Date value) {
            addCriterion("BEFORE_LOGIN_TIME <=", value, "beforeLoginTime");
            return (Criteria) this;
        }

        public Criteria andBeforeLoginTimeIn(List<Date> values) {
            addCriterion("BEFORE_LOGIN_TIME in", values, "beforeLoginTime");
            return (Criteria) this;
        }

        public Criteria andBeforeLoginTimeNotIn(List<Date> values) {
            addCriterion("BEFORE_LOGIN_TIME not in", values, "beforeLoginTime");
            return (Criteria) this;
        }

        public Criteria andBeforeLoginTimeBetween(Date value1, Date value2) {
            addCriterion("BEFORE_LOGIN_TIME between", value1, value2, "beforeLoginTime");
            return (Criteria) this;
        }

        public Criteria andBeforeLoginTimeNotBetween(Date value1, Date value2) {
            addCriterion("BEFORE_LOGIN_TIME not between", value1, value2, "beforeLoginTime");
            return (Criteria) this;
        }

        public Criteria andObject1IsNull() {
            addCriterion("OBJECT1 is null");
            return (Criteria) this;
        }

        public Criteria andObject1IsNotNull() {
            addCriterion("OBJECT1 is not null");
            return (Criteria) this;
        }

        public Criteria andObject1EqualTo(String value) {
            addCriterion("OBJECT1 =", value, "object1");
            return (Criteria) this;
        }

        public Criteria andObject1NotEqualTo(String value) {
            addCriterion("OBJECT1 <>", value, "object1");
            return (Criteria) this;
        }

        public Criteria andObject1GreaterThan(String value) {
            addCriterion("OBJECT1 >", value, "object1");
            return (Criteria) this;
        }

        public Criteria andObject1GreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT1 >=", value, "object1");
            return (Criteria) this;
        }

        public Criteria andObject1LessThan(String value) {
            addCriterion("OBJECT1 <", value, "object1");
            return (Criteria) this;
        }

        public Criteria andObject1LessThanOrEqualTo(String value) {
            addCriterion("OBJECT1 <=", value, "object1");
            return (Criteria) this;
        }

        public Criteria andObject1Like(String value) {
            addCriterion("OBJECT1 like", value, "object1");
            return (Criteria) this;
        }

        public Criteria andObject1NotLike(String value) {
            addCriterion("OBJECT1 not like", value, "object1");
            return (Criteria) this;
        }

        public Criteria andObject1In(List<String> values) {
            addCriterion("OBJECT1 in", values, "object1");
            return (Criteria) this;
        }

        public Criteria andObject1NotIn(List<String> values) {
            addCriterion("OBJECT1 not in", values, "object1");
            return (Criteria) this;
        }

        public Criteria andObject1Between(String value1, String value2) {
            addCriterion("OBJECT1 between", value1, value2, "object1");
            return (Criteria) this;
        }

        public Criteria andObject1NotBetween(String value1, String value2) {
            addCriterion("OBJECT1 not between", value1, value2, "object1");
            return (Criteria) this;
        }

        public Criteria andObject2IsNull() {
            addCriterion("OBJECT2 is null");
            return (Criteria) this;
        }

        public Criteria andObject2IsNotNull() {
            addCriterion("OBJECT2 is not null");
            return (Criteria) this;
        }

        public Criteria andObject2EqualTo(String value) {
            addCriterion("OBJECT2 =", value, "object2");
            return (Criteria) this;
        }

        public Criteria andObject2NotEqualTo(String value) {
            addCriterion("OBJECT2 <>", value, "object2");
            return (Criteria) this;
        }

        public Criteria andObject2GreaterThan(String value) {
            addCriterion("OBJECT2 >", value, "object2");
            return (Criteria) this;
        }

        public Criteria andObject2GreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT2 >=", value, "object2");
            return (Criteria) this;
        }

        public Criteria andObject2LessThan(String value) {
            addCriterion("OBJECT2 <", value, "object2");
            return (Criteria) this;
        }

        public Criteria andObject2LessThanOrEqualTo(String value) {
            addCriterion("OBJECT2 <=", value, "object2");
            return (Criteria) this;
        }

        public Criteria andObject2Like(String value) {
            addCriterion("OBJECT2 like", value, "object2");
            return (Criteria) this;
        }

        public Criteria andObject2NotLike(String value) {
            addCriterion("OBJECT2 not like", value, "object2");
            return (Criteria) this;
        }

        public Criteria andObject2In(List<String> values) {
            addCriterion("OBJECT2 in", values, "object2");
            return (Criteria) this;
        }

        public Criteria andObject2NotIn(List<String> values) {
            addCriterion("OBJECT2 not in", values, "object2");
            return (Criteria) this;
        }

        public Criteria andObject2Between(String value1, String value2) {
            addCriterion("OBJECT2 between", value1, value2, "object2");
            return (Criteria) this;
        }

        public Criteria andObject2NotBetween(String value1, String value2) {
            addCriterion("OBJECT2 not between", value1, value2, "object2");
            return (Criteria) this;
        }

        public Criteria andObject3IsNull() {
            addCriterion("OBJECT3 is null");
            return (Criteria) this;
        }

        public Criteria andObject3IsNotNull() {
            addCriterion("OBJECT3 is not null");
            return (Criteria) this;
        }

        public Criteria andObject3EqualTo(String value) {
            addCriterion("OBJECT3 =", value, "object3");
            return (Criteria) this;
        }

        public Criteria andObject3NotEqualTo(String value) {
            addCriterion("OBJECT3 <>", value, "object3");
            return (Criteria) this;
        }

        public Criteria andObject3GreaterThan(String value) {
            addCriterion("OBJECT3 >", value, "object3");
            return (Criteria) this;
        }

        public Criteria andObject3GreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT3 >=", value, "object3");
            return (Criteria) this;
        }

        public Criteria andObject3LessThan(String value) {
            addCriterion("OBJECT3 <", value, "object3");
            return (Criteria) this;
        }

        public Criteria andObject3LessThanOrEqualTo(String value) {
            addCriterion("OBJECT3 <=", value, "object3");
            return (Criteria) this;
        }

        public Criteria andObject3Like(String value) {
            addCriterion("OBJECT3 like", value, "object3");
            return (Criteria) this;
        }

        public Criteria andObject3NotLike(String value) {
            addCriterion("OBJECT3 not like", value, "object3");
            return (Criteria) this;
        }

        public Criteria andObject3In(List<String> values) {
            addCriterion("OBJECT3 in", values, "object3");
            return (Criteria) this;
        }

        public Criteria andObject3NotIn(List<String> values) {
            addCriterion("OBJECT3 not in", values, "object3");
            return (Criteria) this;
        }

        public Criteria andObject3Between(String value1, String value2) {
            addCriterion("OBJECT3 between", value1, value2, "object3");
            return (Criteria) this;
        }

        public Criteria andObject3NotBetween(String value1, String value2) {
            addCriterion("OBJECT3 not between", value1, value2, "object3");
            return (Criteria) this;
        }

        public Criteria andObject4IsNull() {
            addCriterion("OBJECT4 is null");
            return (Criteria) this;
        }

        public Criteria andObject4IsNotNull() {
            addCriterion("OBJECT4 is not null");
            return (Criteria) this;
        }

        public Criteria andObject4EqualTo(String value) {
            addCriterion("OBJECT4 =", value, "object4");
            return (Criteria) this;
        }

        public Criteria andObject4NotEqualTo(String value) {
            addCriterion("OBJECT4 <>", value, "object4");
            return (Criteria) this;
        }

        public Criteria andObject4GreaterThan(String value) {
            addCriterion("OBJECT4 >", value, "object4");
            return (Criteria) this;
        }

        public Criteria andObject4GreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT4 >=", value, "object4");
            return (Criteria) this;
        }

        public Criteria andObject4LessThan(String value) {
            addCriterion("OBJECT4 <", value, "object4");
            return (Criteria) this;
        }

        public Criteria andObject4LessThanOrEqualTo(String value) {
            addCriterion("OBJECT4 <=", value, "object4");
            return (Criteria) this;
        }

        public Criteria andObject4Like(String value) {
            addCriterion("OBJECT4 like", value, "object4");
            return (Criteria) this;
        }

        public Criteria andObject4NotLike(String value) {
            addCriterion("OBJECT4 not like", value, "object4");
            return (Criteria) this;
        }

        public Criteria andObject4In(List<String> values) {
            addCriterion("OBJECT4 in", values, "object4");
            return (Criteria) this;
        }

        public Criteria andObject4NotIn(List<String> values) {
            addCriterion("OBJECT4 not in", values, "object4");
            return (Criteria) this;
        }

        public Criteria andObject4Between(String value1, String value2) {
            addCriterion("OBJECT4 between", value1, value2, "object4");
            return (Criteria) this;
        }

        public Criteria andObject4NotBetween(String value1, String value2) {
            addCriterion("OBJECT4 not between", value1, value2, "object4");
            return (Criteria) this;
        }

        public Criteria andObject5IsNull() {
            addCriterion("OBJECT5 is null");
            return (Criteria) this;
        }

        public Criteria andObject5IsNotNull() {
            addCriterion("OBJECT5 is not null");
            return (Criteria) this;
        }

        public Criteria andObject5EqualTo(String value) {
            addCriterion("OBJECT5 =", value, "object5");
            return (Criteria) this;
        }

        public Criteria andObject5NotEqualTo(String value) {
            addCriterion("OBJECT5 <>", value, "object5");
            return (Criteria) this;
        }

        public Criteria andObject5GreaterThan(String value) {
            addCriterion("OBJECT5 >", value, "object5");
            return (Criteria) this;
        }

        public Criteria andObject5GreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT5 >=", value, "object5");
            return (Criteria) this;
        }

        public Criteria andObject5LessThan(String value) {
            addCriterion("OBJECT5 <", value, "object5");
            return (Criteria) this;
        }

        public Criteria andObject5LessThanOrEqualTo(String value) {
            addCriterion("OBJECT5 <=", value, "object5");
            return (Criteria) this;
        }

        public Criteria andObject5Like(String value) {
            addCriterion("OBJECT5 like", value, "object5");
            return (Criteria) this;
        }

        public Criteria andObject5NotLike(String value) {
            addCriterion("OBJECT5 not like", value, "object5");
            return (Criteria) this;
        }

        public Criteria andObject5In(List<String> values) {
            addCriterion("OBJECT5 in", values, "object5");
            return (Criteria) this;
        }

        public Criteria andObject5NotIn(List<String> values) {
            addCriterion("OBJECT5 not in", values, "object5");
            return (Criteria) this;
        }

        public Criteria andObject5Between(String value1, String value2) {
            addCriterion("OBJECT5 between", value1, value2, "object5");
            return (Criteria) this;
        }

        public Criteria andObject5NotBetween(String value1, String value2) {
            addCriterion("OBJECT5 not between", value1, value2, "object5");
            return (Criteria) this;
        }

        public Criteria andObject6IsNull() {
            addCriterion("OBJECT6 is null");
            return (Criteria) this;
        }

        public Criteria andObject6IsNotNull() {
            addCriterion("OBJECT6 is not null");
            return (Criteria) this;
        }

        public Criteria andObject6EqualTo(String value) {
            addCriterion("OBJECT6 =", value, "object6");
            return (Criteria) this;
        }

        public Criteria andObject6NotEqualTo(String value) {
            addCriterion("OBJECT6 <>", value, "object6");
            return (Criteria) this;
        }

        public Criteria andObject6GreaterThan(String value) {
            addCriterion("OBJECT6 >", value, "object6");
            return (Criteria) this;
        }

        public Criteria andObject6GreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT6 >=", value, "object6");
            return (Criteria) this;
        }

        public Criteria andObject6LessThan(String value) {
            addCriterion("OBJECT6 <", value, "object6");
            return (Criteria) this;
        }

        public Criteria andObject6LessThanOrEqualTo(String value) {
            addCriterion("OBJECT6 <=", value, "object6");
            return (Criteria) this;
        }

        public Criteria andObject6Like(String value) {
            addCriterion("OBJECT6 like", value, "object6");
            return (Criteria) this;
        }

        public Criteria andObject6NotLike(String value) {
            addCriterion("OBJECT6 not like", value, "object6");
            return (Criteria) this;
        }

        public Criteria andObject6In(List<String> values) {
            addCriterion("OBJECT6 in", values, "object6");
            return (Criteria) this;
        }

        public Criteria andObject6NotIn(List<String> values) {
            addCriterion("OBJECT6 not in", values, "object6");
            return (Criteria) this;
        }

        public Criteria andObject6Between(String value1, String value2) {
            addCriterion("OBJECT6 between", value1, value2, "object6");
            return (Criteria) this;
        }

        public Criteria andObject6NotBetween(String value1, String value2) {
            addCriterion("OBJECT6 not between", value1, value2, "object6");
            return (Criteria) this;
        }

        public Criteria andObject7IsNull() {
            addCriterion("OBJECT7 is null");
            return (Criteria) this;
        }

        public Criteria andObject7IsNotNull() {
            addCriterion("OBJECT7 is not null");
            return (Criteria) this;
        }

        public Criteria andObject7EqualTo(String value) {
            addCriterion("OBJECT7 =", value, "object7");
            return (Criteria) this;
        }

        public Criteria andObject7NotEqualTo(String value) {
            addCriterion("OBJECT7 <>", value, "object7");
            return (Criteria) this;
        }

        public Criteria andObject7GreaterThan(String value) {
            addCriterion("OBJECT7 >", value, "object7");
            return (Criteria) this;
        }

        public Criteria andObject7GreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT7 >=", value, "object7");
            return (Criteria) this;
        }

        public Criteria andObject7LessThan(String value) {
            addCriterion("OBJECT7 <", value, "object7");
            return (Criteria) this;
        }

        public Criteria andObject7LessThanOrEqualTo(String value) {
            addCriterion("OBJECT7 <=", value, "object7");
            return (Criteria) this;
        }

        public Criteria andObject7Like(String value) {
            addCriterion("OBJECT7 like", value, "object7");
            return (Criteria) this;
        }

        public Criteria andObject7NotLike(String value) {
            addCriterion("OBJECT7 not like", value, "object7");
            return (Criteria) this;
        }

        public Criteria andObject7In(List<String> values) {
            addCriterion("OBJECT7 in", values, "object7");
            return (Criteria) this;
        }

        public Criteria andObject7NotIn(List<String> values) {
            addCriterion("OBJECT7 not in", values, "object7");
            return (Criteria) this;
        }

        public Criteria andObject7Between(String value1, String value2) {
            addCriterion("OBJECT7 between", value1, value2, "object7");
            return (Criteria) this;
        }

        public Criteria andObject7NotBetween(String value1, String value2) {
            addCriterion("OBJECT7 not between", value1, value2, "object7");
            return (Criteria) this;
        }

        public Criteria andObject8IsNull() {
            addCriterion("OBJECT8 is null");
            return (Criteria) this;
        }

        public Criteria andObject8IsNotNull() {
            addCriterion("OBJECT8 is not null");
            return (Criteria) this;
        }

        public Criteria andObject8EqualTo(String value) {
            addCriterion("OBJECT8 =", value, "object8");
            return (Criteria) this;
        }

        public Criteria andObject8NotEqualTo(String value) {
            addCriterion("OBJECT8 <>", value, "object8");
            return (Criteria) this;
        }

        public Criteria andObject8GreaterThan(String value) {
            addCriterion("OBJECT8 >", value, "object8");
            return (Criteria) this;
        }

        public Criteria andObject8GreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT8 >=", value, "object8");
            return (Criteria) this;
        }

        public Criteria andObject8LessThan(String value) {
            addCriterion("OBJECT8 <", value, "object8");
            return (Criteria) this;
        }

        public Criteria andObject8LessThanOrEqualTo(String value) {
            addCriterion("OBJECT8 <=", value, "object8");
            return (Criteria) this;
        }

        public Criteria andObject8Like(String value) {
            addCriterion("OBJECT8 like", value, "object8");
            return (Criteria) this;
        }

        public Criteria andObject8NotLike(String value) {
            addCriterion("OBJECT8 not like", value, "object8");
            return (Criteria) this;
        }

        public Criteria andObject8In(List<String> values) {
            addCriterion("OBJECT8 in", values, "object8");
            return (Criteria) this;
        }

        public Criteria andObject8NotIn(List<String> values) {
            addCriterion("OBJECT8 not in", values, "object8");
            return (Criteria) this;
        }

        public Criteria andObject8Between(String value1, String value2) {
            addCriterion("OBJECT8 between", value1, value2, "object8");
            return (Criteria) this;
        }

        public Criteria andObject8NotBetween(String value1, String value2) {
            addCriterion("OBJECT8 not between", value1, value2, "object8");
            return (Criteria) this;
        }

        public Criteria andObject9IsNull() {
            addCriterion("OBJECT9 is null");
            return (Criteria) this;
        }

        public Criteria andObject9IsNotNull() {
            addCriterion("OBJECT9 is not null");
            return (Criteria) this;
        }

        public Criteria andObject9EqualTo(String value) {
            addCriterion("OBJECT9 =", value, "object9");
            return (Criteria) this;
        }

        public Criteria andObject9NotEqualTo(String value) {
            addCriterion("OBJECT9 <>", value, "object9");
            return (Criteria) this;
        }

        public Criteria andObject9GreaterThan(String value) {
            addCriterion("OBJECT9 >", value, "object9");
            return (Criteria) this;
        }

        public Criteria andObject9GreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT9 >=", value, "object9");
            return (Criteria) this;
        }

        public Criteria andObject9LessThan(String value) {
            addCriterion("OBJECT9 <", value, "object9");
            return (Criteria) this;
        }

        public Criteria andObject9LessThanOrEqualTo(String value) {
            addCriterion("OBJECT9 <=", value, "object9");
            return (Criteria) this;
        }

        public Criteria andObject9Like(String value) {
            addCriterion("OBJECT9 like", value, "object9");
            return (Criteria) this;
        }

        public Criteria andObject9NotLike(String value) {
            addCriterion("OBJECT9 not like", value, "object9");
            return (Criteria) this;
        }

        public Criteria andObject9In(List<String> values) {
            addCriterion("OBJECT9 in", values, "object9");
            return (Criteria) this;
        }

        public Criteria andObject9NotIn(List<String> values) {
            addCriterion("OBJECT9 not in", values, "object9");
            return (Criteria) this;
        }

        public Criteria andObject9Between(String value1, String value2) {
            addCriterion("OBJECT9 between", value1, value2, "object9");
            return (Criteria) this;
        }

        public Criteria andObject9NotBetween(String value1, String value2) {
            addCriterion("OBJECT9 not between", value1, value2, "object9");
            return (Criteria) this;
        }

        public Criteria andObject10IsNull() {
            addCriterion("OBJECT10 is null");
            return (Criteria) this;
        }

        public Criteria andObject10IsNotNull() {
            addCriterion("OBJECT10 is not null");
            return (Criteria) this;
        }

        public Criteria andObject10EqualTo(String value) {
            addCriterion("OBJECT10 =", value, "object10");
            return (Criteria) this;
        }

        public Criteria andObject10NotEqualTo(String value) {
            addCriterion("OBJECT10 <>", value, "object10");
            return (Criteria) this;
        }

        public Criteria andObject10GreaterThan(String value) {
            addCriterion("OBJECT10 >", value, "object10");
            return (Criteria) this;
        }

        public Criteria andObject10GreaterThanOrEqualTo(String value) {
            addCriterion("OBJECT10 >=", value, "object10");
            return (Criteria) this;
        }

        public Criteria andObject10LessThan(String value) {
            addCriterion("OBJECT10 <", value, "object10");
            return (Criteria) this;
        }

        public Criteria andObject10LessThanOrEqualTo(String value) {
            addCriterion("OBJECT10 <=", value, "object10");
            return (Criteria) this;
        }

        public Criteria andObject10Like(String value) {
            addCriterion("OBJECT10 like", value, "object10");
            return (Criteria) this;
        }

        public Criteria andObject10NotLike(String value) {
            addCriterion("OBJECT10 not like", value, "object10");
            return (Criteria) this;
        }

        public Criteria andObject10In(List<String> values) {
            addCriterion("OBJECT10 in", values, "object10");
            return (Criteria) this;
        }

        public Criteria andObject10NotIn(List<String> values) {
            addCriterion("OBJECT10 not in", values, "object10");
            return (Criteria) this;
        }

        public Criteria andObject10Between(String value1, String value2) {
            addCriterion("OBJECT10 between", value1, value2, "object10");
            return (Criteria) this;
        }

        public Criteria andObject10NotBetween(String value1, String value2) {
            addCriterion("OBJECT10 not between", value1, value2, "object10");
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
package com.xtel.vngolf.api.listener.entities;

	import java.util.Date;

	public class CmsUser {
		private int user_id;
		private String user_name;
		private String full_name;
		private String birthday;
		private String email;
		private String phone_number;
		private String mobile_number;
		private int sex;
		private String address;
		private String unit;
		private int status;
		private String create_time;
		private String create_by;
		private Date update_time;
		private String update_by;
		private int schedule_id;

		public CmsUser() {
			super();
		}

		public int getUser_id() {
			return user_id;
		}

		public void setUser_id(int id) {
			this.user_id = id;
		}

		public String getUser_name() {
			return user_name;
		}

		public void setUser_name(String user_name) {
			this.user_name = user_name;
		}

		public String getFull_name() {
			return full_name;
		}

		public void setFull_name(String full_name) {
			this.full_name = full_name;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhone_number() {
			return phone_number;
		}

		public void setPhone_number(String phone_int) {
			this.phone_number = phone_int;
		}

		public String getMobile_number() {
			return mobile_number;
		}

		public void setMobile_number(String mobile_int) {
			this.mobile_number = mobile_int;
		}

		public int getSex() {
			return sex;
		}

		public void setSex(int sex) {
			this.sex = sex;
		}

		public String getAddress() {
			return address;
		}

		public void setAddress(String address) {
			this.address = address;
		}

		public String getUnit() {
			return unit;
		}

		public void setUnit(String unit) {
			this.unit = unit;
		}

		public int getStatus() {
			return status;
		}

		public void setStatus(int status) {
			this.status = status;
		}

		public String getCreate_time() {
			return create_time;
		}

		public void setCreate_time(String create_time) {
			this.create_time = create_time;
		}

		public String getCreate_by() {
			return create_by;
		}

		public void setCreate_by(String create_by) {
			this.create_by = create_by;
		}

		public Date getUpdate_time() {
			return update_time;
		}

		public void setUpdate_time(Date update_time) {
			this.update_time = update_time;
		}

		public String getUpdate_by() {
			return update_by;
		}

		public void setUpdate_by(String update_by) {
			this.update_by = update_by;
		}

		public int getSchedule_id() {
			return schedule_id;
		}

		public void setSchedule_id(int schedule_id) {
			this.schedule_id = schedule_id;
		}

		@Override
		public String toString() {
			return "CmsUser [user_id=" + user_id + ", user_name=" + user_name + ", full_name=" + full_name + ", birthday="
					+ birthday + ", email=" + email + ", phone_number=" + phone_number + ", mobile_number=" + mobile_number
					+ ", sex=" + sex + ", address=" + address + ", unit=" + unit + ", status=" + status + ", create_time="
					+ create_time + ", create_by=" + create_by + ", update_time=" + update_time + ", update_by=" + update_by
					+ ", schedule_id=" + schedule_id + "]";
		}
	}

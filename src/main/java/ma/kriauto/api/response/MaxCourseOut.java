package ma.kriauto.api.response;

public class MaxCourseOut extends CommonOut {
     
	private Double dailycourse;
	private Double totalcourse;
	private Integer isrolling;
	
	public Double getDailycourse() {
		return dailycourse;
	}
	public void setDailycourse(Double dailycourse) {
		this.dailycourse = dailycourse;
	}
	public Double getTotalcourse() {
		return totalcourse;
	}
	public void setTotalcourse(Double totalcourse) {
		this.totalcourse = totalcourse;
	}
	public Integer getIsrolling() {
		return isrolling;
	}
	public void setIsrolling(Integer isrolling) {
		this.isrolling = isrolling;
	}
	
	@Override
	public String toString() {
		return "MaxCourseOut [dailycourse=" + dailycourse + ", totalcourse=" + totalcourse + ", isrolling=" + isrolling
				+ "]";
	}
}

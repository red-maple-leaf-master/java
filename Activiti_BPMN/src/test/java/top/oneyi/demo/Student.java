package top.oneyi.demo;
 
import java.util.HashMap;
 
public class Student {
	private String name;
	private final String nickName = "12";
	private HashMap<String,Object> otherInfo  = new HashMap<String,Object>();
	

	public Student() {
		super();
	}
	public Student(String name, String nickName, HashMap<String, Object> otherInfo) {
		super();
		this.name = name;
		this.otherInfo = otherInfo;
	}

	public String getNickName() {
		return nickName;
	}

	public String getName() {
		return name;
	}
 
	public void setName(String name) {
		this.name = name;
	}
 
	public HashMap<String, Object> getOtherInfo() {
		return otherInfo;
	}
 
	public void setOtherInfo(HashMap<String, Object> otherInfo) {
		this.otherInfo = otherInfo;
	}
 
	@Override
	public String toString() {
		return "Student [name=" + name + ", otherInfo=" + otherInfo + ", nickName=" + nickName +"]";
	}
 
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((otherInfo == null) ? 0 : otherInfo.hashCode());
		return result;
	}
 
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (otherInfo == null) {
			if (other.otherInfo != null)
				return false;
		} else if (!otherInfo.equals(other.otherInfo))
			return false;
		return true;
	}
}
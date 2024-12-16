package vn.dodientu.dto;

public class Response {
	 private String result;   // JWT token
	    private String message;
	    private String role;     // Vai trò của người dùng

	    // Getter and Setter methods
	    public String getResult() {
	        return result;
	    }

	    public void setResult(String result) {
	        this.result = result;
	    }

	    public String getMessage() {
	        return message;
	    }

	    public void setMessage(String message) {
	        this.message = message;
	    }

	    public String getRole() {
	        return role;
	    }

	    public void setRole(String role) {
	        this.role = role;
	    }
}

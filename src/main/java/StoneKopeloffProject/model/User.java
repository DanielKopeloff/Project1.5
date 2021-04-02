package StoneKopeloffProject.model;


import javax.persistence.*;

@Entity
@Table(name = "users")
public class User {

	// Main primary Key used by the DB
	@Id
	@GeneratedValue
	@Column(name = "id")
	private int userIDPK;
	/**
	 * Right now this is not being used but i would like to abstract this in the future but for right now
	 * it works by using the Pk of the object since it is a One to One relationship
	 * In hibernate it always uses the Pk so it makes this field irrelevant
	 */
	// This will be the user Name given to the user if they want to look themselves up by the ID
	// Will have the properties of a Pk but not actually be the PK

//	@Column(nullable = false,columnDefinition="serial")
//	private int user_id;

	@Column(name = "username" ,unique = true)
	private String username;

	@Column(name = "password")
	private String password;
	@Column(name = "firstname")
	private String firstname;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "email")
	private String email;

	@Column(name = "roleId")
	private int role_id;

	public User() {
		//No-arg constructor
	}

	public User(int user_id, String username, String password, String firstname, String lastname, String email,
			int role_id) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;

	}


	public User(String username, String password, String firstname, String lastname, String email, int role) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.email = email;
		this.role_id = role;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getUserIDPK() {
		return userIDPK;
	}

	public void setUserIDPK(int userIDPK) {
		this.userIDPK = userIDPK;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public void setRole_Value(Role roleName){
		switch (roleName){
			case AUTHOR:
				this.role_id = Role.AUTHOR.ordinal();
				break;
			case RESOLVER:
				this.role_id = Role.RESOLVER.ordinal();
			default:
				System.out.println("Not a valid role");
				this.role_id =-1;
				break;

		}
	}



	 public enum Role {
		AUTHOR,
		RESOLVER
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + role_id;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (role_id != other.role_id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [ username=" + username + ", password=" + password + ", firstname="
				+ firstname + ", lastname=" + lastname + ", email=" + email + ", role_id=" + role_id + "]";
	}
}
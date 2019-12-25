class AppUser {
    constructor(id, username, email, dob, usertype, active, joindate, updateddate, blocked) {
        this.id = id
        this.username = username
        this.email = email
        this.dob = dob
        this.usertype = usertype
        this.active = active
        this.joindate = joindate
        this.updatedate = updateddate
        this.blocked = blocked;
    }
}

export default AppUser

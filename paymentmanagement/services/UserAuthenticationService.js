import Axios from 'axios'
import Constants from '../utils/Constants'

class UserAuthenticationService {
    
    constructor(token) {
        this.token = token
    }

    fetchUserDetails() {
        return new Promise((resolve, reject) => {
            Axios.get(`http://${Constants.USERMANANAGEMENT_URL}/users`, {
                headers: { Authorization: this.token}
            }).then(response => {
                resolve(response.data)
            }).catch((error) => {
                console.error(error)
                reject()
            })
        })
    }
}

export default UserAuthenticationService

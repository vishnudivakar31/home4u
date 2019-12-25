import Axios from 'axios'
import { resolve } from 'url'

class UserAuthenticationService {
    
    constructor(token) {
        this.token = token
    }

    fetchUserDetails() {
        return new Promise((resolve, reject) => {
            console.log('token', this.token)
            Axios.get('http://192.168.1.54:6060/users', {
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

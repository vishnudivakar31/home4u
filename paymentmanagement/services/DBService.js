import { Client } from 'pg'
import Constants from '../utils/Constants'

class DBService {
    
    constructor() {
        this.client = new Client({
            user: Constants.PG_USER,
            host: Constants.PG_HOST,
            database: Constants.DB_NAME,
            password: Constants.PG_PWD,
            port: Constants.PG_PORT
        })
        this.client.connect()
    }

    executeQuery(query) {
        return new Promise((resolve, reject) => {
            this.client.query(query, (err, res) => {
                if(err) {
                    reject(err)
                } else {
                    resolve(res)
                }
            })
        })
    }
}

export default DBService

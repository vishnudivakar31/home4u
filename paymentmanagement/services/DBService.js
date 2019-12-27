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
            this.client.query(query)
            .then(res => {
                if(res.rowCount !== null) {
                    resolve(res)
                }
            })
            .catch(err => {
                console.error(err)
                reject(err)
            })
        })
    }
}

export default DBService

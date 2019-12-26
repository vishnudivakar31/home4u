import PaymentDetails from '../models/PaymentDetails'
import DBService from './DBService'
import DateSeeker from '../utils/DateSeeker'

class PaymentServices {

    constructor(user, payload) {
        this.user = user
        this.paymentDetails = new PaymentDetails()
        for(let item in payload) {
            this.paymentDetails[item] = payload[item]
        }
        this.paymentDetails.user_id = user.id
        this.paymentDetails.enrolled_date = new DateSeeker().date
        this.paymentDetails.active = true
        this.dbService = new DBService()
    }

    insertToTable() {
        return new Promise((resolve, reject) => {
            let query = this.paymentDetails.getInsertQuery()
            this.dbService.executeQuery(query)
            .then(res => {
                resolve(res)
            })
            .catch(error => {
                reject(error.detail)
            })
        })
    }

    updateTable() {
        return new Promise((resolve, reject) => {
            let query = this.paymentDetails.updateQuery()
            this.dbService.executeQuery(query)
            .then(res => {
                resolve(res)
            })
            .catch(error => {
                reject(error.detail)
            })
        })
    }

    deleteTable() {
        return new Promise((resolve, reject) => {
            let query = this.paymentDetails.deleteQuery()
            this.dbService.executeQuery(query)
            .then(res => {
                resolve(res)
            })
            .catch(error => {
                reject(error.detail)
            })
        })
    }

    reviveTable() {
        return new Promise((resolve, reject) => {
            let query = this.paymentDetails.reviveQuery()
            this.dbService.executeQuery(query)
            .then(res => {
                resolve(res)
            })
            .catch(error => {
                reject(error.detail)
            })
        })
    }
}

export default PaymentServices

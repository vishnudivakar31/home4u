import Autopay from '../models/Autopay'
import PaymentServices from './PaymentServices'
import DBService from './DBService'
import Strings from '../utils/Strings'

class AutopayServices {
    
    constructor(appUser, payload) {
        this.appUser = appUser
        this.autopay = new Autopay()
        for(let item in payload) {
            this.autopay[item] = payload[item]
        }
        this.autopay.active = true
        this.dbService = new DBService()
    }

    registerAutopay() {
        return new Promise((resolve, reject) => {
            let paymentService = new PaymentServices(this.appUser, [])
            paymentService.getFromTable()
            .then(res => {
                if(res.rows.length > 0) {
                    let payment_id = res.rows[0].id
                    this.autopay.payment_id = payment_id
                    let query = this.autopay.insertQuery()
                    this.dbService.executeQuery(query)
                    .then(result => resolve(result))
                    .catch(error => reject(error))
                } else {
                    reject(Strings.DOC_NOTFOUND)
                }
            })
            .catch(err => reject(err))
        })
    }

    getAutopayDetails() {
        return new Promise((resolve, reject) => {
            let paymentService = new PaymentServices(this.appUser, [])
            paymentService.getFromTable()
            .then(res => {
                if(res.rows.length > 0) {
                    let payment_id = res.rows[0].id
                    this.autopay.payment_id = payment_id
                    let query = this.autopay.getQuery()
                    this.dbService.executeQuery(query)
                    .then(result => resolve(result))
                    .catch(error => reject(error))
                } else {
                    reject(Strings.DOC_NOTFOUND)
                }
            })
            .catch(err => reject(err))
        })
    }

    updateAutopayDetails() {
        return new Promise((resolve, reject) => {
            let paymentService = new PaymentServices(this.appUser, [])
            paymentService.getFromTable()
            .then(res => {
                if(res.rows.length > 0) {
                    let payment_id = res.rows[0].id
                    this.autopay.payment_id = payment_id
                    let query = this.autopay.updateQuery()
                    this.dbService.executeQuery(query)
                    .then(result => resolve(result))
                    .catch(error => reject(error))
                } else {
                    reject(Strings.DOC_NOTFOUND)
                }
            })
            .catch(err => reject(err))
        })
    }

    deleteAutoPayDetails() {
        return new Promise((resolve, reject) => {
            let paymentService = new PaymentServices(this.appUser, [])
            paymentService.getFromTable()
            .then(res => {
                if(res.rows.length > 0) {
                    let payment_id = res.rows[0].id
                    this.autopay.payment_id = payment_id
                    let query = this.autopay.deleteQuery()
                    this.dbService.executeQuery(query)
                    .then(result => resolve(result))
                    .catch(error => reject(error))
                } else {
                    reject(Strings.DOC_NOTFOUND)
                }
            })
            .catch(err => reject(err))
        })
    }

    reviveAutoPayDetails() {
        return new Promise((resolve, reject) => {
            let paymentService = new PaymentServices(this.appUser, [])
            paymentService.getFromTable()
            .then(res => {
                if(res.rows.length > 0) {
                    let payment_id = res.rows[0].id
                    this.autopay.payment_id = payment_id
                    let query = this.autopay.reviveQuery()
                    this.dbService.executeQuery(query)
                    .then(result => resolve(result))
                    .catch(error => reject(error))
                } else {
                    reject(Strings.DOC_NOTFOUND)
                }
            })
            .catch(err => reject(err))
        })
    }
}

export default AutopayServices

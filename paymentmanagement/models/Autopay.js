import Queries from '../utils/Queries'

class Autopay {

    constructor(id, amount, payment_date, payment_id, active) {
        this.id = id
        this.amount = amount
        this.payment_date = payment_date
        this.payment_id = payment_id
        this.active = active
    }

    insertQuery() {
        return `${Queries.INSERT_TO_AUTOPAY} (DEFAULT, ${this.amount}, '${this.payment_date}', 
            ${this.payment_id}, ${this.active});`
    }

    getQuery() {
        return `${Queries.GET_AUTOPAY_DETAILS}${this.payment_id};`
    }

    updateQuery() {
        return `${Queries.UPDATE_AUTOPAY_DETAILS} amount=${this.amount}, payment_date='${this.payment_date}' WHERE payment_id=${this.payment_id};`
    }

    deleteQuery() {
        return `${Queries.DELETE_AUTOPAY_DETAILS}${this.payment_id};`
    }

    reviveQuery() {
        return `${Queries.REVIVE_AUTOPAY_DETAILS}${this.payment_id};`
    }

}

export default Autopay

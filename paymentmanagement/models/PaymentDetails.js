import Queries from '../utils/Queries'

class PaymentDetails {

    constructor(id, user_id, billing_address, card_no, expiry_date, cvv, enrolled_date, active) {
        this.id = id
        this.user_id = user_id
        this.billing_address = billing_address
        this.card_no = card_no
        this.expiry_date = expiry_date
        this.cvv = cvv
        this.enrolled_date = enrolled_date
        this.active = active
    }

    getInsertQuery() {
        return `${Queries.INSERT_TO_PAYMENTDETAILS} (DEFAULT, ${this.user_id}, '${this.billing_address}', 
            '${this.card_no}', '${this.expiry_date}', ${this.cvv}, '${this.enrolled_date}', ${this.active});`
    }

    updateQuery() {
        return `${Queries.UPDATE_PAYMENTDETAILS} billing_address='${this.billing_address}', card_no='${this.card_no}'
        , expiry_date='${this.expiry_date}', cvv=${this.cvv} WHERE user_id=${this.user_id};`
    }

    deleteQuery() {
        return `${Queries.DELETE_PAYMENTDETAILS}${this.user_id};`
    }

    reviveQuery() {
        return `${Queries.REVIVE_PAYMENTDETAILS}${this.user_id};`
    }

    getQuery() {
        return `${Queries.GET_PAYMENTDETAILS}${this.user_id};`
    }
}

export default PaymentDetails

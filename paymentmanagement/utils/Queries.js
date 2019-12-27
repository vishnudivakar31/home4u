const Queries = {
    INIT_QUERIES: {
        CREATE_PAYMENT: `CREATE TABLE IF NOT EXISTS paymentdetails(
            id SERIAL PRIMARY KEY,
            user_id BIGINT NOT NULL UNIQUE,
            billing_address TEXT NOT NULL,
            card_no TEXT NOT NULL,
            expiry_date DATE NOT NULL,
            cvv INT NOT NULL,
            enrolled_date DATE NOT NULL,
            active BOOL NOT NULL
        );`,
        CREATE_BILLING: `CREATE TABLE IF NOT EXISTS billing(
            id SERIAL PRIMARY KEY,
            payment_id INT NOT NULL,
            room_id INT NOT NULL,
            no_of_days_stay FLOAT NOT NULL,
            price FLOAT NOT NULL,
            tax FLOAT NOT NULL,
            total_price FLOAT NOT NULL,
            date DATE NOT NULL,
            payment_received BOOL NOT NULL
        );`,
        CREATE_AUTOPAY: `CREATE TABLE IF NOT EXISTS autopay(
            id SERIAL PRIMARY KEY,
            amount FLOAT NOT NULL,
            payment_date DATE NOT NULL,
            payment_id INT NOT NULL UNIQUE,
            active BOOL NOT NULL
        );`
    },
    INSERT_TO_PAYMENTDETAILS: `INSERT INTO paymentdetails VALUES`,
    UPDATE_PAYMENTDETAILS: `UPDATE paymentdetails set`,
    DELETE_PAYMENTDETAILS: `UPDATE paymentdetails set active=false where user_id=`,
    REVIVE_PAYMENTDETAILS: `UPDATE paymentdetails set active=true where user_id=`,
    GET_PAYMENTDETAILS: `SELECT * FROM paymentdetails where user_id=`,
    INSERT_TO_AUTOPAY: `INSERT INTO autopay VALUES`,
    GET_AUTOPAY_DETAILS: `SELECT * FROM autopay where payment_id=`,
    UPDATE_AUTOPAY_DETAILS: `UPDATE autopay set`,
    DELETE_AUTOPAY_DETAILS: `UPDATE autopay set active=false where payment_id=`,
    REVIVE_AUTOPAY_DETAILS: `UPDATE autopay set active=true where payment_id=`
}

module.exports = Queries

const Queries = {
    CREATE_PAYMENT: `CREATE TABLE IF NOT EXISTS paymentdetails(
        id SERIAL PRIMARY KEY,
        user_id BIGINT NOT NULL,
        billing_address TEXT NOT NULL,
        card_no TEXT NOT NULL,
        expiry_date DATE NOT NULL,
        cvv INT NOT NULL,
        autopay BOOL NOT NULL,
        enrolled_date DATE NOT NULL 
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
    );`
}

module.exports = Queries

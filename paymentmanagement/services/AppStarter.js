import DBService from './DBService'
import Queries from '../utils/Queries'

class AppStarter {

    constructor() {
        this.dbService = new DBService()
    }

    initialize() {
        this.dbService.executeQuery(Queries.CREATE_PAYMENT)
        this.dbService.executeQuery(Queries.CREATE_BILLING)
    }
}

export default AppStarter

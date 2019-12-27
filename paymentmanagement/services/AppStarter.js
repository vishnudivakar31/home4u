import DBService from './DBService'
import Queries from '../utils/Queries'

class AppStarter {

    constructor() {
        this.dbService = new DBService()
    }

    initialize() {
        let initQueries = Queries.INIT_QUERIES
        for(let query in initQueries) {
            this.dbService.executeQuery(initQueries[query])
        }
    }
}

export default AppStarter

import Express from 'express'
import AutopayServices from '../services/AutopayServices'
import Strings from '../utils/Strings'

const router = Express.Router()

router.post('/registration', (req, res) => {
    let appUser = req.app.get('appUser')
    let autopayService = new AutopayServices(appUser, req.body)
    autopayService.registerAutopay()
    .then(result => res.status(200).json(Strings.ACCEPTED))
    .catch(err => res.status(500).json(err))
})

router.get('', (req, res) => {
    let appUser = req.app.get('appUser')
    let autopayService = new AutopayServices(appUser, req.body)
    autopayService.getAutopayDetails()
    .then(result => res.status(200).json(result.rows[0]))
    .catch(err => res.status(500).json(err))
})

router.put('', (req, res) => {
    let appUser = req.app.get('appUser')
    let autopayService = new AutopayServices(appUser, req.body)
    autopayService.updateAutopayDetails()
    .then(result => res.status(200).json(result.rows[0]))
    .catch(err => res.status(500).json(err))
})

router.delete('', (req, res) => {
    let appUser = req.app.get('appUser')
    let autopayService = new AutopayServices(appUser, req.body)
    autopayService.deleteAutoPayDetails()
    .then(result => res.status(200).json(result.rows[0]))
    .catch(err => res.status(500).json(err))
})

router.get('/revive', (req, res) => {
    let appUser = req.app.get('appUser')
    let autopayService = new AutopayServices(appUser, req.body)
    autopayService.reviveAutoPayDetails()
    .then(result => res.status(200).json(result.rows[0]))
    .catch(err => res.status(500).json(err))
})

export default router

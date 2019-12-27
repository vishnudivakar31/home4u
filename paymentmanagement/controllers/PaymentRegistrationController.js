import Express from 'express'
import PaymentService from '../services/PaymentServices'
import Strings from '../utils/Strings'

const router = Express.Router()

router.post('/registration', (req, res) => {
    let appUser = req.app.get('appUser')
    let paymentService = new PaymentService(appUser, req.body)
    paymentService.insertToTable()
    .then(result => res.status(200).send(Strings.ACCEPTED))
    .catch(error => res.status(500).send(error))
})

router.get('', (req, res) => {
    let appUser = req.app.get('appUser')
    let paymentService = new PaymentService(appUser, req.body)
    paymentService.getFromTable()
    .then(result => res.status(200).send(result.rows[0]))
    .catch(error => res.status(500).send(error))
})

router.put('', (req, res) => {
    let appUser = req.app.get('appUser')
    let paymentService = new PaymentService(appUser, req.body)
    paymentService.updateTable()
    .then(result => res.status(200).send(Strings.ACCEPTED))
    .catch(error => res.status(500).send(error))
})

router.delete('', (req, res) => {
    let appUser = req.app.get('appUser')
    let paymentService = new PaymentService(appUser, req.body)
    paymentService.deleteTable()
    .then(result => res.status(200).send(Strings.DELETED))
    .catch(error => res.status(500).send(error))
})

router.get('/revive', (req, res) => {
    let appUser = req.app.get('appUser')
    let paymentService = new PaymentService(appUser, req.body)
    paymentService.reviveTable()
    .then(result => res.status(200).send(Strings.ACCEPTED))
    .catch(error => res.status(500).send(error))
})

export default router

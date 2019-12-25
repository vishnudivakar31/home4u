import Express from 'express'

const router = Express.Router()

router.get('', (req, res) => {
    let appUser = req.app.get('appUser')
    res.send(appUser)
})

export default router

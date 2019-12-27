import PaymentRouter from './controllers/PaymentRegistrationController'
import UserAuthenticationService from './services/UserAuthenticationService'
import AppUser from './models/AppUser'
import AppStarter from './services/AppStarter'
import AutopayRouter from './controllers/AutopayController'

var express = require('express');
var cookieParser = require('cookie-parser');
var logger = require('morgan');

var app = express();

app.use(logger('dev'));
app.use(express.json());
app.use(express.urlencoded({ extended: false }));
app.use(cookieParser());

let appUser

app.use(function(req, res, next) {
    let userService = new UserAuthenticationService(req.headers.authorization)
    userService.fetchUserDetails()
    .then(user => {
        appUser = new AppUser(
            user.id, 
            user.username,
            user.email,
            user.dob,
            user.usertype,
            user.active,
            user.joindate,
            user.updateddate,
            user.blocked
        )
        app.set('appUser', appUser)
        next()
    })
    .catch(error => {
        res.status(401).json('Unauthorized user.')
    })
})

let appStarter = new AppStarter()
appStarter.initialize()

app.use('/payment', PaymentRouter)
app.use('/autopay', AutopayRouter)

module.exports = app;

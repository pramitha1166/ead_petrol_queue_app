const express = require('express')
const router = express.Router()

router.post('/login', (req, res) => {

    let body = {}

    body = {
        user_type: "shed_owner",
        token: "abcd"
    }

    setTimeout(() => {
        res.status(200).json({title: "Success", body: body})
    }, 5000)
    
})

router.post('/register', (req, res) => {

    let body = {}

    body = {
        user_type: "shed_owner",
        token: "abcd"
    }

    setTimeout(() => {
        res.status(200).json({title: "Success", body: body})
    }, 5000)
    
})

module.exports = router
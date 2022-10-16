const express = require('express')
const router = express.Router()

router.post('/login', (req, res) => {
    res.status(200).json({title: "Success"})
})

module.exports = router
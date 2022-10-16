const express = require('express')
const mongoose = require('mongoose')
const userRoutes = require('./src/api/user_routes')
const morgan = require('morgan')

const app = express()

app.use(morgan('dev'))
app.use(express.json())
app.use('/api/user', userRoutes)

app.listen(3000, () => {
    console.log("Server stated on " + 3000)
})
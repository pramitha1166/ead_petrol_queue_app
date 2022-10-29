"use strict"

const mongoose = require('mongoose')

const User_Schema = new mongoose.Schema({
    username:  {
        type: String,
        required: true
    },
    password: {
        type: String
    },
}, {timestamps: true})

module.exports = mongoose.model("User", User_Schema)
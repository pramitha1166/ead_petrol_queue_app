"use strict";
const mongoose = require("mongoose");

const userSchema = new mongoose.Schema({
  firstName: {
    type: String,
    required: true,
    min: 4,
    max: 100,
  },
  lastName: {
    type: String,
    required: true,
    min: 4,
    max: 255,
  },
  arrivalTime: {
    type: String,
    required: true,
    min: 4,
    max: 255,
  },
  leaveTime: {
    type: String,
    required: true,
    min: 4,
    max: 255,
  },
  reportedFuelCount: {
    type: String,
    required: true,
    min: 4,
    max: 255,
  },
  reportedFuelType: {
    type: String,
    required: true,
    min: 4,
    max: 255,
  },
  queuLength: {
    type: String,
    required: true,
    min: 4,
    max: 255,
  },
});

module.exports = mongoose.model("user", userSchema);
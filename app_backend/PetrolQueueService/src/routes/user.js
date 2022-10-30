const express = require("express");
const router = express.Router();

const {
  login,
  register,
  queulength,
  petrol_count,
  deisel_count,
  leave,
  arrival,
  shed_owner_arrival,
  shed_owner_leave
} = require("./../controller/user");

router.post("/login", login);
router.post("/register", register);

//user
router.post("/viewqueulegnth", queulength);
router.post("/petrol_count", petrol_count);
router.post("/diesel_count", deisel_count);
router.post("/leave", leave);
router.post("/arrival", arrival); 

//shed owner
router.post("/shed/addarrivaltime", shed_owner_arrival); 
router.post("/shed/addleavetime", shed_owner_leave);

module.exports = router;

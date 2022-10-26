const express = require("express");
const router = express.Router();
const validateToken = require("./../util/validateToken");
const {
  getArrivalTime,
  getQueuLenth,
  getLeaveTime,
  updateArrivalTime,
  updateQueuLength,
  updateLeaveTime,
} = require("./../service/UserService");

/**
 * This function is only for accepting /api/user/updateArrivalTime requests for adding user's arrival time
 * The request body must contain all the attributes of model user arrival time
 * see model user arrival time @path {src/model/userController.js}
 * This function can use any method implementation inside the service layer for adding user arrival time to any given relational or none relational database
 * see userUserTimeService @path {src/service/userService.js}
 *
 * @method { HTTP PATCH }
 * @url {/api/user/updateArrivalTime}
 * @urlparams {none}
 * @return {json} {Newly added product}
 */
router.patch("/addarrivaltime", validateToken, async (req, res) => {
  try {
    const result = await updateArrivalTime(req.body, req.data);
    res.status(201).json({ result });
  } catch (err) {
    console.log(err)
    res.status(400).json(err);
  }
});

/**
 * This function is only for accepting /api/user/updateQueuLength requests for update queu length time
 * The request body must contain all the attributes of model queu length
 * see model queu length time @path {src/model/userController.js}
 * This function can use any method implementation inside the service layer for adding user arrival time to any given relational or none relational database
 * see userUserTimeService @path {src/service/userService.js}
 *
 * @method { HTTP PATCH }
 * @url {/api/user/updateQuueLength}
 * @urlparams {none}
 * @return {json} {updated Queu Length}
 */
 router.patch("/updateQueLength", validateToken, async (req, res) => {
  try {
    const result = await updateQueuLength(req.body, req.data);
    res.status(201).json({ result });
  } catch (err) {
    console.log(err)
    res.status(400).json(err);
  }
});

/**
 * This function is only for accepting /api/user/updateLeaveTime requests for adding user's leave time
 * The request body must contain all the attributes of model user leaveTime time
 * see model user leave time @path {src/model/userController.js}
 * This function can use any method implementation inside the service layer for adding user leave time to any given relational or none relational database
 * see userLeaveTimeService @path {src/service/userService.js}
 *
 * @method { HTTP PATCH }
 * @url {/api/user/updateLeaveTime}
 * @urlparams {none}
 * @return {json} {updated leave product}
 */
 router.patch("/updateleavetime", validateToken, async (req, res) => {
  try {
    const result = await updateLeaveTime(req.body, req.data);
    res.status(201).json({ result });
  } catch (err) {
    console.log(err)
    res.status(400).json(err);
  }
});

/**
 * This function is only for accepting /api/user/view/userid?page=m&limit=n requests for view leave time of a specific user
 * The request should not contain any body
 * This function can use any method implementation inside the service layer for view leave time of a specific seller
 * see userService @path {src/service/userService.js}
 *
 * @method { HTTP GET }
 * @url {/api/user/view/:userid?page=m&limit=n}
 * @urlparams {integer userid}
 * @urlparams {integer m - page number} - pagination page number
 * @urlparams {integer n - limit} -  pagination item limit
 * @return {json} {leave time for the specified userId with pagination}
 */
router.get("/viewqueulegnth/:userid", async (req, res) => {
  try {
    const result = await getLeaveTime(req.params.userId, req.query);
    res.status(200).json({ result });
  } catch (err) {
    res.status(400).json(err);
  }
});

/**
 * This function is only for accepting /api/user/view/userid?page=m&limit=n requests for view queu legnth of a specific user
 * The request should not contain any body
 * This function can use any method implementation inside the service layer for view queu legnth of a specific seller
 * see userService @path {src/service/userService.js}
 *
 * @method { HTTP GET }
 * @url {/api/user/view/:userid?page=m&limit=n}
 * @urlparams {integer userid}
 * @urlparams {integer m - page number} - pagination page number
 * @urlparams {integer n - limit} -  pagination item limit
 * @return {json} {queu legnth for the specified userId with pagination}
 */
 router.get("/viewqueulength/:userid", async (req, res) => {
  try {
    const result = await getQueuLenth(req.params.userId, req.query);
    res.status(200).json({ result });
  } catch (err) {
    res.status(400).json(err);
  }
});

/**
 * This function is only for accepting /api/user/view/userid?page=m&limit=n requests for view arival time of a specific user
 * The request should not contain any body
 * This function can use any method implementation inside the service layer for view queu legnth of a specific seller
 * see userService @path {src/service/userService.js}
 *
 * @method { HTTP GET }
 * @url {/api/user/view/:userid?page=m&limit=n}
 * @urlparams {integer userid}
 * @urlparams {integer m - page number} - pagination page number
 * @urlparams {integer n - limit} -  pagination item limit
 * @return {json} {arival time for the specified userId with pagination}
 */
 router.get("/viewarrival/:userid", async (req, res) => {
  try {
    const result = await getArrivalTime(req.params.userId, req.query);
    res.status(200).json({ result });
  } catch (err) {
    res.status(400).json(err);
  }
});

/**
 * This function is only for accepting /api/user/view/userid?page=m&limit=n requests for view arival time of a specific user
 * The request should not contain any body
 * This function can use any method implementation inside the service layer for view queu legnth of a specific seller
 * see userService @path {src/service/userService.js}
 *
 * @method { HTTP GET }
 * @url {/api/user/view/:userid?page=m&limit=n}
 * @urlparams {integer userid}
 * @urlparams {integer m - page number} - pagination page number
 * @urlparams {integer n - limit} -  pagination item limit
 * @return {json} {arival time for the specified userId with pagination}
 */
 router.get("/getFuelArrivaltime/", async (req, res) => {
  try {
    const result = await getFuelArrivalTime(req.params.userId, req.query);
    res.status(200).json({ result });
  } catch (err) {
    res.status(400).json(err);
  }
});

/**
 * This function is only for accepting /api/user/view/userid?page=m&limit=n requests for view arival time of a specific user
 * The request should not contain any body
 * This function can use any method implementation inside the service layer for view queu legnth of a specific seller
 * see userService @path {src/service/userService.js}
 *
 * @method { HTTP GET }
 * @url {/api/user/view/:userid?page=m&limit=n}
 * @urlparams {integer userid}
 * @urlparams {integer m - page number} - pagination page number
 * @urlparams {integer n - limit} -  pagination item limit
 * @return {json} {arival time for the specified userId with pagination}
 */
 router.get("/getFuelRanOutOfTime/", async (req, res) => {
  try {
    const result = await getFuelRanOutOfTime(req.params.userId, req.query);
    res.status(200).json({ result });
  } catch (err) {
    res.status(400).json(err);
  }
});

module.exports = router;

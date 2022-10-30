const {
  loginService,
  registerService,
  queulengthService,
  petrol_countService,
  diesel_countService,
  leaveService,
  arrivalService,
  shed_owner_arrivalService,
  shed_owner_leave_service
} = require("./../service/user");

exports.login = async (req, res) => {
  try {
    const result = await loginService(req.body, req.data);
    res.json(result);
  } catch (err) {
    return res.status(400).json({
      error: err,
    });
  }
};

exports.register = async (req, res) => {
  try {
    const result = await registerService(req.body, req.data);
    res.json(result);
  } catch (err) {
    return res.status(400).json({
      error: err,
    });
  }
};

exports.queulength = async (req, res) => {
  try {
    const result = await queulengthService(req.body, req.data);
    res.json(result);
  } catch (err) {
    return res.status(400).json({
      error: err,
    });
  }
};

exports.petrol_count = async (req, res) => {
  try {
    const result = await petrol_countService(req.body, req.data);
    res.json(result);
  } catch (err) {
    return res.status(400).json({
      error: err,
    });
  }
};

exports.deisel_count = async (req, res) => {
  try {
    const result = await diesel_countService(req.body, req.data);
    res.json(result);
  } catch (err) {
    return res.status(400).json({
      error: err,
    });
  }
};

exports.leave = async (req, res) => {
  try {
    const result = await leaveService(req.body, req.data);
    res.json(result);
  } catch (err) {
    return res.status(400).json({
      error: err,
    });
  }
};

exports.arrival = async (req, res) => {
  try {
    const result = await arrivalService(req.body, req.data);
    res.json(result);
  } catch (err) {
    return res.status(400).json({
      error: err,
    });
  }
};

exports.shed_owner_arrival = async (req, res) => {
  try {
    const result = await shed_owner_arrivalService(req.body, req.data);
    res.json(result);
  } catch (err) {
    return res.status(400).json({
      error: err,
    });
  }
};

exports.shed_owner_leave = async (req, res) => {
    try {
      const result = await shed_owner_leave_service(req.body, req.data);
      res.json(result);
    } catch (err) {
      return res.status(400).json({
        error: err,
      });
    }
  };
  
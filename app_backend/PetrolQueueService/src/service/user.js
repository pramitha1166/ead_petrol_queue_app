const { JWT_TOKEN } = require("./../lib/constants");

exports.loginService = (query) => {
  return new Promise((resolve, reject) => {
    try {
      let limit = query.limit ? query.limit : 100;

      Order.find()
        .limit(limit)
        .exec((err, order) => {
          if (err) {
          } else {
            resolve(order);
          }
        });
    } catch (e) {}
    resolve(JWT_TOKEN);
  });
};

exports.registerService = (query) => {
  return new Promise((resolve, reject) => {
    try {
      let limit = query.limit ? query.limit : 100;

      Order.find()
        .limit(limit)
        .exec((err, order) => {
          if (err) {
          } else {
            resolve(order);
          }
        });
    } catch (e) {}
    resolve("success11");
  });
};

exports.queulengthService = (query) => {
  return new Promise((resolve, reject) => {
    try {
      let limit = query.limit ? query.limit : 100;

      Order.find()
        .limit(limit)
        .exec((err, order) => {
          if (err) {
          } else {
            resolve(order);
          }
        });
    } catch (e) {}
    resolve("12");
  });
};

exports.petrol_countService = (query) => {
  return new Promise((resolve, reject) => {
    try {
      let limit = query.limit ? query.limit : 100;

      Order.find()
        .limit(limit)
        .exec((err, order) => {
          if (err) {
          } else {
            resolve(order);
          }
        });
    } catch (e) {}
    resolve("1200L");
  });
};

exports.diesel_countService = (query) => {
  return new Promise((resolve, reject) => {
    try {
      let limit = query.limit ? query.limit : 100;

      Order.find()
        .limit(limit)
        .exec((err, order) => {
          if (err) {
          } else {
            resolve(order);
          }
        });
    } catch (e) {}
    resolve("12000L");
  });
};

exports.leaveService = (query) => {
  return new Promise((resolve, reject) => {
    try {
      let limit = query.limit ? query.limit : 100;

      Order.find()
        .limit(limit)
        .exec((err, order) => {
          if (err) {
          } else {
            resolve(order);
          }
        });
    } catch (e) {}
    resolve(new Date());
  });
};

exports.arrivalService = (query) => {
  return new Promise((resolve, reject) => {
    try {
      let limit = query.limit ? query.limit : 100;

      Order.find()
        .limit(limit)
        .exec((err, order) => {
          if (err) {
          } else {
            resolve(order);
          }
        });
    } catch (e) {}
    resolve(new Date() + 2);
  });
};

exports.shed_owner_arrivalService = (query) => {
  return new Promise((resolve, reject) => {
    try {
      let limit = query.limit ? query.limit : 100;

      Order.find()
        .limit(limit)
        .exec((err, order) => {
          if (err) {
          } else {
            resolve(order);
          }
        });
    } catch (e) {}
    resolve(new Date() + 2);
  });
};

exports.shed_owner_leave_service = (query) => {
  return new Promise((resolve, reject) => {
    try {
      let limit = query.limit ? query.limit : 100;

      Order.find()
        .limit(limit)
        .exec((err, order) => {
          if (err) {
          } else {
            resolve(order);
          }
        });
    } catch (e) {}
    resolve(new Date() + 2);
  });
};

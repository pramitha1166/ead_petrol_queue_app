"use strict";

const {
  userValidateAdd,
  userValidateUpdate,
} = require("./../util/userValidation");
const Product = require("./../model/user");
const { paginationControl } = require("./../util/paginationControl");

/**
 * Current implementation for add user to mopngodb using mongoose
 * uses mongoose @visit {https://www.npmjs.com/package/mongoose} for object mappping
 *
 * @params {userData - An instance of user model}
 * @params {payload - payload given by the JWT vertification}
 * @return {promise} {resolve upon successfull add or reject if there is any error}
 */
const updateArrivalTime = (userData, payload) => {
  return new Promise(async (resolve, reject) => {
    //validate inputs
    const validate = userValidateAdd(Data);
    if (validate.error !== undefined) {
      reject(validate.error.details[0].message); //reject if there is any validation error
    }

    try {
      if (userData.ownerRef !== payload._id) {
        reject("unauthorized access");
      } else {
        const user = new User(userData);
        const savedArrivalDataData = await userArrival.save();
        resolve(savedArrivalDataData);
      }
    } catch (err) {
      console.log(err)
      reject(err);
    }
  });
};

/**
 * Current implementation for add user to mopngodb using mongoose
 * uses mongoose @visit {https://www.npmjs.com/package/mongoose} for object mappping
 *
 * @params {userData - An instance of user model}
 * @params {payload - payload given by the JWT vertification}
 * @return {promise} {resolve upon successfull add or reject if there is any error}
 */
 const updateQueuLength = (userData, payload) => {
  return new Promise(async (resolve, reject) => {
    //validate inputs
    const validate = userValidateAdd(Data);
    if (validate.error !== undefined) {
      reject(validate.error.details[0].message); //reject if there is any validation error
    }

    try {
      if (userData.ownerRef !== payload._id) {
        reject("unauthorized access");
      } else {
        const user = new User(userData);
        const savedQueueLengthDataData = await userEqueLength.save();
        resolve(savedQueueLengthDataData);
      }
    } catch (err) {
      console.log(err)
      reject(err);
    }
  });
};

/**
 * Current implementation for add user to mopngodb using mongoose
 * uses mongoose @visit {https://www.npmjs.com/package/mongoose} for object mappping
 *
 * @params {userData - An instance of user model}
 * @params {payload - payload given by the JWT vertification}
 * @return {promise} {resolve upon successfull add or reject if there is any error}
 */
 const updateLeaveTime = (userData, payload) => {
  return new Promise(async (resolve, reject) => {
    //validate inputs
    const validate = userValidateAdd(Data);
    if (validate.error !== undefined) {
      reject(validate.error.details[0].message); //reject if there is any validation error
    }

    try {
      if (userData.ownerRef !== payload._id) {
        reject("unauthorized access");
      } else {
        const user = new User(userData);
        const savedLeaveTimeDataData = await userLeaveTime.save();
        resolve(savedLeaveTimeDataData);
      }
    } catch (err) {
      console.log(err)
      reject(err);
    }
  });
};

/**
 * Current implementation for view user by id from the mongodb database
 * uses mongoose @visit {https://www.npmjs.com/package/mongoose} for object mappping
 *
 * @params {item id}
 * @return {promise} {resolve user data reject if there is any error}
 */
const getArrivalTime = (itemId) => {
  return new Promise(async (resolve, reject) => {
    try {
      const userData = await User.findOne({ _id: itemId }); //get all user
      resolve(userData);
    } catch (err) {
      reject(err);
    }
  });
};
/**
 * Current implementation for view user by id from the mongodb database
 * uses mongoose @visit {https://www.npmjs.com/package/mongoose} for object mappping
 *
 * @params {item id}
 * @return {promise} {resolve user data reject if there is any error}
 */
 const getQueuLenth = (itemId) => {
  return new Promise(async (resolve, reject) => {
    try {
      const userData = await User.findOne({ _id: itemId }); //get all user
      resolve(userData);
    } catch (err) {
      reject(err);
    }
  });
};

/**
 * Current implementation for view user by id from the mongodb database
 * uses mongoose @visit {https://www.npmjs.com/package/mongoose} for object mappping
 *
 * @params {item id}
 * @return {promise} {resolve user data reject if there is any error}
 */
 const getLeaveTime = (itemId) => {
  return new Promise(async (resolve, reject) => {
    try {
      const userData = await User.findOne({ _id: itemId }); //get all user
      resolve(userData);
    } catch (err) {
      reject(err);
    }
  });
};

module.exports.addProduct = addProduct;
module.exports.getProducts = getProducts;
module.exports.addImage = addImage;
module.exports.removeImage = removeImage;
module.exports.getSellerProducts = getSellerProducts;
module.exports.deleteProduct = deleteProduct;
module.exports.updateProduct = updateProduct;
module.exports.viewProductById = viewProductById;

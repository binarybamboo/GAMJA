const httpStatus = require('http-status');
const mongoose = require('mongoose');
const logger = require('../config/logger');
const { User } = require('../models');
const ApiError = require('../utils/ApiError');

const getUserByEmail = async userBody => {
  return User.findOne({ email: userBody });
};
const getUserById = async userBody => {
  return User.findOne({ _id: mongoose.Types.ObjectId(userBody) });
};
const createUser = async userBody => {
  const userData = new User();
  // eslint-disable-next-line no-underscore-dangle
  userData._id = new mongoose.Types.ObjectId();
  userData.email = `${userBody.email}`;
  userData.nickname = '';
  if (await User.isEmailTaken(userData.email)) {
    throw new ApiError(httpStatus.BAD_REQUEST, 'Email already taken');
  }
  logger.info(userData);
  return userData.save();
};

module.exports = {
  getUserByEmail,
  createUser,
  getUserById,
};

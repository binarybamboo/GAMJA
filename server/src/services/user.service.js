const httpStatus = require('http-status');
const { User } = require('../models');
const ApiError = require('../utils/ApiError');

const createUser = async userBody => {
  const body = userBody;
  body.email = `kakao_${userBody.email}`;
  if (await User.isEmailTaken(body.email)) {
    throw new ApiError(httpStatus.BAD_REQUEST, 'Email already taken');
  }
  return User.create(body);
};

module.exports = {
  createUser,
};

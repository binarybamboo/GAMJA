const httpStatus = require('http-status');
const pick = require('../utils/pick');
const ApiError = require('../utils/ApiError');
const catchAsync = require('../utils/catchAsync');
const { userService } = require('../services');
const logger = require('../config/logger');

const changeName = catchAsync(async (req, res) => {
  logger.info(req.user);
  const user = await userService.changeName(req);
  res.status(200).send();
});

module.exports = {
  changeName,
};

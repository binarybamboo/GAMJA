const httpStatus = require('http-status');
const { getUserByEmail, createUser } = require('./user.service');
const Token = require('../models/tokens.model');
const tokenService = require('./token.service');
const userService = require('./user.service');
const ApiError = require('../utils/ApiError');
const { tokenTypes } = require('../config/tokens');
const logger = require('../config/logger');

const prefixKakao = 'kakao_';

const login = async userBody => {
  const body = userBody;
  body.email = prefixKakao + body.email;
  logger.info(body.email);
  let user = await getUserByEmail(body.email);
  logger.info(user);
  if (!user) {
    user = createUser(body);
  }
  return user;
};
const logout = async refreshToken => {
  const refreshTokenDoc = await Token.findOne({
    token: refreshToken,
    type: tokenTypes.REFRESH,
    blacklisted: false,
  });
  if (!refreshTokenDoc) {
    throw new ApiError(httpStatus.NOT_FOUND, 'Not found');
  }
  await refreshTokenDoc.remove();
};

const refreshAuth = async refreshToken => {
  try {
    const refreshTokenDoc = await tokenService.verifyToken(
      refreshToken,
      tokenTypes.REFRESH,
    );
    const user = await userService.getUserById(refreshTokenDoc.user);
    logger.info(user);
    if (!user) {
      throw new Error();
    }
    await refreshTokenDoc.remove();
    return tokenService.generateAuthTokens(user);
  } catch (error) {
    throw new ApiError(httpStatus.UNAUTHORIZED, 'Please authenticate');
  }
};

module.exports = {
  login,
  logout,
  refreshAuth,
};

const { getUserByEmail, createUser } = require('./user.service');
const logger = require('../config/logger');

const login = async userBody => {
  let user = await getUserByEmail(userBody);
  if (!user) {
    user = createUser(userBody);
  }
  return user;
};

module.exports = {
  login,
};

const httpStatus = require('http-status');
const pick = require('../utils/pick');
const ApiError = require('../utils/ApiError');
const catchAsync = require('../utils/catchAsync');
const { diaryGroupService } = require('../services');
const logger = require('../config/logger');

const createDiaryGroup = catchAsync(async (req, res) => {
  const user = await diaryGroupService.createDiaryGroup(req);
  res.status(httpStatus.CREATED).send(user);
});

const getDiaryGroups = catchAsync(async (req, res) => {
  const result = await diaryGroupService.getDiaryGroups(req);
  res.send(result);
});

const updateDiaryGroup = catchAsync(async (req, res) => {
  const user = await diaryGroupService.updateDiaryGroupById(req);

  res.send(user);
});

const deleteDiaryGroup = catchAsync(async (req, res) => {
  await diaryGroupService.deleteDiaryGroupById(req);
  res.status(httpStatus.NO_CONTENT).send();
});

module.exports = {
  createDiaryGroup,
  getDiaryGroups,
  updateDiaryGroup,
  deleteDiaryGroup,
};

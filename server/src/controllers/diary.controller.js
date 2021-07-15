const httpStatus = require('http-status');
const catchAsync = require('../utils/catchAsync');
const { diaryService } = require('../services');

const createDiary = catchAsync(async (req, res) => {
  const user = await diaryService.createDiary(req);
  res.status(httpStatus.CREATED).send(user);
});

const getDiary = catchAsync(async (req, res) => {
  const result = await diaryService.getDiary(req);
  res.send(result);
});

const updateDiary = catchAsync(async (req, res) => {
  const user = await diaryService.updateDiaryById(req);
  res.send(user);
});

const deleteDiary = catchAsync(async (req, res) => {
  await diaryService.deleteDiaryById(req);
  res.status(httpStatus.NO_CONTENT).send();
});

module.exports = {
  createDiary,
  getDiary,
  updateDiary,
  deleteDiary,
};

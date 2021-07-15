/* eslint-disable no-underscore-dangle */
const mongoose = require('mongoose');
const { DiaryGroup } = require('../models');
const logger = require('../config/logger');

const createDiaryGroup = async req => {
  return DiaryGroup.create({
    name: req.body.name,
    description: req.body.description,
    author: mongoose.Types.ObjectId(req.user._id),
  });
};

const getDiaryGroups = async req => {
  return DiaryGroup.find({ author: req.user._id });
};

const updateDiaryGroupById = async req => {
  return DiaryGroup.findOneAndUpdate(
    {
      _id: req.params.diaryGroupId,
      author: mongoose.Types.ObjectId(req.user._id),
    },
    { $set: req.body },
    { returnNewDocument: true },
  );
};

const deleteDiaryGroupById = async req => {
  return DiaryGroup.deleteOne({
    _id: req.params.diaryGroupId,
    author: mongoose.Types.ObjectId(req.user._id),
  });
};

const addDiary = async (groupId, diaryId) => {
  const diaryGroup = await DiaryGroup.findOne({ _id: groupId });
  const diaryList = [...diaryGroup.diary, diaryId];
  logger.info(diaryList);
  return diaryGroup.update({ diary: diaryList });
};

const printAll = async id => {
  const group = await DiaryGroup.findOne({ _id: id }).populate(
    'diary',
    'photo state name description createdAt updatedAt',
  );
  return group.diary;
};

module.exports = {
  createDiaryGroup,
  getDiaryGroups,
  updateDiaryGroupById,
  deleteDiaryGroupById,
  addDiary,
  printAll,
};

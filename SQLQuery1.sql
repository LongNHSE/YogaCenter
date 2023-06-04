SELECT lopHoc.maLopHoc, maSlot,lopHoc.maRoom, room.maRoom, timeTable.ngayHoc,timeTable.thu
FROM ([dbo].[lopHoc] INNER JOIN [dbo].[room] On lopHoc.maRoom=room.maRoom)
INNER JOIN [dbo].[timeTable] On lopHoc.maLopHoc=timeTable.maLopHoc
WHERE lopHoc.maRoom ='RO0001'  AND maSlot ='SL001' and thu='MONDAY' OR thu='WEDNESDAY'

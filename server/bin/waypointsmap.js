function() { 
  for (var idx=0; idx < this.wayPoints.length;idx++) { 
    emit( this.wayPoints[idx]._id, 1 );
  } 
}
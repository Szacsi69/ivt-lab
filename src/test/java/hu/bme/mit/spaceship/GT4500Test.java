package hu.bme.mit.spaceship;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class GT4500Test {

  private GT4500 ship;

  private TorpedoStore primary;
  private TorpedoStore secondary;

  @BeforeEach
  public void init(){
    primary = Mockito.mock(TorpedoStore.class);
    secondary = Mockito.mock(TorpedoStore.class);

    this.ship = new GT4500(primary, secondary);
  }

  @Test
  public void fireTorpedo_Single_Success(){
    // Arrange
    Mockito.when(primary.isEmpty()).thenReturn(false);
    Mockito.when(primary.fire(1)).thenReturn(true);

    Mockito.when(secondary.isEmpty()).thenReturn(false);
    Mockito.when(secondary.fire(1)).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.SINGLE);

    // Assert
    assertEquals(true, result);
    verify(primary, times(1)).isEmpty();
    verify(primary, times(1)).fire(1);
  }

  @Test
  public void fireTorpedo_All_Success(){
    // Arrange
    Mockito.when(primary.isEmpty()).thenReturn(false);
    Mockito.when(primary.getTorpedoCount()).thenReturn(10);
    Mockito.when(primary.fire(primary.getTorpedoCount())).thenReturn(true);

    Mockito.when(secondary.isEmpty()).thenReturn(false);
    Mockito.when(secondary.getTorpedoCount()).thenReturn(10);
    Mockito.when(secondary.fire(secondary.getTorpedoCount())).thenReturn(true);

    // Act
    boolean result = ship.fireTorpedo(FiringMode.ALL);

    // Assert
    assertEquals(true, result);

    verify(primary, times(1)).isEmpty();
    verify(primary, times(2)).getTorpedoCount();
    verify(primary, times(2)).fire(primary.getTorpedoCount());

    verify(secondary, times(1)).isEmpty();
    verify(secondary, times(2)).getTorpedoCount();
    verify(secondary, times(2)).fire(secondary.getTorpedoCount());
  }

}

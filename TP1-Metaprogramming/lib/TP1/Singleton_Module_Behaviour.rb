module TP

  module Singleton_Module

    attr_accessor :singleton_module

    # TODO: Buscar una forma de no repetir codigo
    # If the module is included by a class, it defines behaviour for every new instance
    def self.included(including_class)
      including_class.singleton_class.class_eval{attr_accessor :singleton_module

                                                  def singleton_module

                                                    if !@singleton_module then
                                                      @singleton_module = Module.new
                                                      self.extend @singleton_module
                                                    end

                                                    @singleton_module

                                                  end

                                                  }
    end

    def singleton_module

      if !@singleton_module then
        @singleton_module = Module.new
        self.extend @singleton_module
      end

      @singleton_module

    end

  end

end